package ua.com.alevel.starteducation.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.request.StudentDtoRequest;
import ua.com.alevel.starteducation.dto.response.StudentDtoResponse;
import ua.com.alevel.starteducation.facade.StudentFacade;
import ua.com.alevel.starteducation.model.Student;
import ua.com.alevel.starteducation.service.StudentService;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;


    public StudentFacadeImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void create(StudentDtoRequest dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        studentService.create(student);
    }

    @Override
    public void update(StudentDtoRequest dto, Long id) {
        Student student = studentService.findById(id);
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        studentService.update(student);
    }

    @Override
    public void delete(Long id) {
        if (studentService.existById(id)) {
            studentService.delete(id);
        }
    }

    @Override
    public StudentDtoResponse findById(Long id) {
        return new StudentDtoResponse(studentService.findById(id));
    }

    @Override
    public Page<StudentDtoResponse> findAll(Pageable pageable) {
        return studentService.findAll(pageable);
    }

    @Override
    public Page<StudentDtoResponse> findAllByTeacher(Long teacherId, Pageable pageable) {
        return studentService.findAllByTeacher(teacherId, pageable);
    }
}
