package ua.com.alevel.starteducation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ua.com.alevel.starteducation.dto.request.ChangeUserPasswordRequest;
import ua.com.alevel.starteducation.dto.request.OverrideUserPasswordRequest;
import ua.com.alevel.starteducation.dto.request.SaveUserRequest;
import ua.com.alevel.starteducation.dto.response.UserDtoResponse;

import java.util.Optional;


public interface UserService extends UserDetailsService{

    @Transactional(readOnly = true)
    UserDetails loadUserByUserName(String email) throws NotFoundException;

    Page<UserDtoResponse> list(Pageable pageable);

    Optional<UserDtoResponse> findById(long id);

    Optional<UserDtoResponse> findByEmail(String email);

    UserDtoResponse create(SaveUserRequest request);

    UserDtoResponse createAdmin(SaveUserRequest request);

    UserDtoResponse createTeacher(SaveUserRequest request);

    UserDtoResponse createStudent(SaveUserRequest request);

    UserDtoResponse changePasswordById(long id, OverrideUserPasswordRequest request);

    UserDtoResponse changePasswordByEmail(String email, ChangeUserPasswordRequest request);

    void deleteById(long id);

    void deleteByEmail(String email);

}