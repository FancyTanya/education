package ua.com.alevel.starteducation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.starteducation.dto.request.StudentDtoRequest;
import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.StudentDtoResponse;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.model.Student;

public interface StudentService extends CrudService<Student> {

    Page<StudentDtoResponse> findAllByTeacher(Long teacherId, Pageable pageable);

    StudentDtoResponse create(StudentDtoRequest request);
}
