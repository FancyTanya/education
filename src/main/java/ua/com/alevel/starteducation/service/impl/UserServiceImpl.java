package ua.com.alevel.starteducation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ua.com.alevel.starteducation.dto.request.ChangeUserPasswordRequest;
import ua.com.alevel.starteducation.dto.request.OverrideUserPasswordRequest;
import ua.com.alevel.starteducation.dto.request.SaveUserRequest;
import ua.com.alevel.starteducation.dto.response.UserDtoResponse;
import ua.com.alevel.starteducation.exceptions.EducationException;
import ua.com.alevel.starteducation.model.auth.EducationUser;
import ua.com.alevel.starteducation.model.auth.EducationUserAuthority;
import ua.com.alevel.starteducation.model.auth.EducationUserDetails;
import ua.com.alevel.starteducation.model.auth.KnownAuthority;
import ua.com.alevel.starteducation.repository.AuthorityRepository;
import ua.com.alevel.starteducation.repository.UserRepository;
import ua.com.alevel.starteducation.service.UserService;

import java.time.OffsetDateTime;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           AuthorityRepository authorityRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUserName(String email) throws NotFoundException {
        EducationUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email " + email + " not found"));
        return new EducationUserDetails(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDtoResponse> list(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDtoResponse::fromUserWithBasicAttributes);
    }

    @Override
    @Transactional
    public Optional<UserDtoResponse> findById(long id) {
        return userRepository.findById(id).map(UserDtoResponse::fromUser);
    }

    @Override
    @Transactional
    public Optional<UserDtoResponse> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDtoResponse::fromUser);
    }

    @Override
    @Transactional
    public UserDtoResponse create(SaveUserRequest request) {
        validateUniqueFields(request);
        return UserDtoResponse.fromUser(save(request, getRegularUserAuthorities()));
    }

    @Override
    @Transactional
    public UserDtoResponse createAdmin(SaveUserRequest request) {
        validateUniqueFields(request);
        return UserDtoResponse.fromUser(save(request, getAdminAuthorities()));
    }

    @Override
    @Transactional
    public UserDtoResponse createTeacher(SaveUserRequest request) {
        validateUniqueFields(request);
        return UserDtoResponse.fromUser(save(request, getAdminAuthorities()));
    }

    @Override
    @Transactional
    public UserDtoResponse createStudent(SaveUserRequest request) {
        validateUniqueFields(request);
        return UserDtoResponse.fromUser(save(request, getStudentAuthorities()));
    }

    @Override
    @Transactional
    public UserDtoResponse changePasswordById(long id, OverrideUserPasswordRequest request) {
        EducationUser user = getUser(id);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return UserDtoResponse.fromUser(user);
    }

    @Override
    @Transactional
    public UserDtoResponse changePasswordByEmail(String email, ChangeUserPasswordRequest request) {
        EducationUser user = getUser(email);
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return UserDtoResponse.fromUser(user);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if (!userRepository.existsById(id)) {
            throw EducationException.userNotFound(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw EducationException.userNotFound(email);
        }
        userRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        EducationUser user =userRepository.findByEmail(name)
                .orElseThrow(() -> new UsernameNotFoundException("User "+ name + " not found"));
        return new EducationUserDetails(user);
    }

    private void validateUniqueFields(SaveUserRequest request) {
        String email = request.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw EducationException.duplicateEmail(email);
        }
    }

    private EducationUser save(SaveUserRequest request, Map<KnownAuthority, EducationUserAuthority> authorityMap) {
        EducationUser user = new EducationUser();
        user.getAuthorities().putAll(authorityMap);
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(OffsetDateTime.now());
        return userRepository.save(user);
    }

    private Map<KnownAuthority, EducationUserAuthority> getStudentAuthorities() {
        EducationUserAuthority authority = authorityRepository
                .findById(KnownAuthority.ROLE_STUDENT)
                .orElseThrow(() -> EducationException.userNotFound(KnownAuthority.ROLE_STUDENT.name()));
        Map<KnownAuthority, EducationUserAuthority> authorityMap = new EnumMap<KnownAuthority, EducationUserAuthority>(KnownAuthority.class);
        authorityMap.put(KnownAuthority.ROLE_STUDENT, authority);
        return authorityMap;
    }

    private Map<KnownAuthority, EducationUserAuthority> getRegularUserAuthorities() {
        EducationUserAuthority authority = authorityRepository
                .findById(KnownAuthority.ROLE_USER)
                .orElseThrow(() ->EducationException.authorityNotFound(KnownAuthority.ROLE_USER.name()));
        Map<KnownAuthority, EducationUserAuthority> authorityMap = new EnumMap<KnownAuthority, EducationUserAuthority>(KnownAuthority.class);
        authorityMap.put(KnownAuthority.ROLE_USER, authority);
        return authorityMap;
    }

    private Map<KnownAuthority, EducationUserAuthority> getAdminAuthorities() {
        return authorityRepository.findAllByIdIn(AuthorityRepository.ADMIN_AUTHORITIES)
                .collect(Collectors.toMap(
                        EducationUserAuthority::getId,
                        Function.identity(),
                        (e1, e2) -> e2,
                        () -> new EnumMap<KnownAuthority, EducationUserAuthority>(KnownAuthority.class)));
    }

    private void changePassword(EducationUser user, String oldPassword, String newPassword) {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw EducationException.wrongPassword();
        }
        user.setPassword(passwordEncoder.encode(newPassword));
    }

    private EducationUser getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> EducationException.userNotFound(id));
    }

    private EducationUser getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> EducationException.userNotFound(email));
    }
}
