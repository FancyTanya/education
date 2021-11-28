package ua.com.alevel.starteducation.service.impl;

import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.exceptions.EducationException;
import ua.com.alevel.starteducation.model.Teacher;
import ua.com.alevel.starteducation.model.auth.EducationUserAuthority;
import ua.com.alevel.starteducation.model.auth.KnownAuthority;
import ua.com.alevel.starteducation.repository.TeacherRepository;
import ua.com.alevel.starteducation.service.TeacherService;

import java.util.EnumMap;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public TeacherServiceImpl(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return teacherRepository.existsById(id);
    }

    @Override
    public Teacher findById(Long id) {
       return teacherRepository.findById(id).orElseThrow(() -> EducationException.userNotFound(id));
    }

    @Override
    @PageableAsQueryParam
    public Page<TeacherDtoResponse> findAll(@Parameter(hidden = true) Pageable pageable) {
        return teacherRepository.findAll(pageable).map(TeacherDtoResponse::fromTeacher);

    }

    @Override
    @Transactional
    public TeacherDtoResponse createTeacher(TeacherDtoRequest request) {
        validateUniqueFields(request);
        return TeacherDtoResponse.fromTeacher(save(request));
    }

    private void validateUniqueFields(TeacherDtoRequest request) {
        String email = request.getEmail();
        if (teacherRepository.existsByEmail(email)) {
            throw EducationException.duplicateEmail(email);
        }
    }

    private Teacher save(TeacherDtoRequest request) {
        Teacher teacher = new Teacher();
        teacher.getRole();
        teacher.setEmail(request.getEmail());
        teacher.setPassword(passwordEncoder.encode(request.getPassword()));
        return teacherRepository.save(teacher);
    }

}
