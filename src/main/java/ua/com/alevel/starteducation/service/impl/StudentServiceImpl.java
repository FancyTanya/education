package ua.com.alevel.starteducation.service.impl;

import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.starteducation.dto.request.StudentDtoRequest;
import ua.com.alevel.starteducation.dto.response.StudentDtoResponse;
import ua.com.alevel.starteducation.dto.response.UserDtoResponse;
import ua.com.alevel.starteducation.exceptions.EducationException;
import ua.com.alevel.starteducation.model.Student;
import ua.com.alevel.starteducation.model.auth.KnownAuthority;
import ua.com.alevel.starteducation.repository.StudentRepository;
import ua.com.alevel.starteducation.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> EducationException.userNotFound(id));
    }

    @Override
    @PageableAsQueryParam
    public Page<Student> findAll(@Parameter(hidden = true) Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    @PageableAsQueryParam
    public Page<StudentDtoResponse> findAllByTeacher(Long teacherId, @Parameter(hidden = true) Pageable pageable) {
        return studentRepository.findAllByTeacher(teacherId, pageable);
    }

    @Override
    @Transactional
    public StudentDtoResponse create(StudentDtoRequest request) {
        validateUniqueFields(request);
        return StudentDtoResponse.fromStudent(save(request));
    }

    private void validateUniqueFields(StudentDtoRequest request) {
        String email = request.getEmail();
        if (studentRepository.existsByEmail(email)) {
            throw EducationException.duplicateEmail(email);
        }
    }

    private Student save(StudentDtoRequest request) {
        Student student = new Student();
        student.getRole();
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));

        return studentRepository.save(student);
    }
}
