package ua.com.alevel.starteducation.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.starteducation.dto.request.StudentDtoRequest;
import ua.com.alevel.starteducation.dto.response.StudentDtoResponse;
import ua.com.alevel.starteducation.model.Student;

import java.util.List;

public interface StudentFacade {

    void create(StudentDtoRequest dto);

    void update(StudentDtoRequest dto, Long id);

    void delete(Long id);

    StudentDtoResponse findById(Long id);

    Page<StudentDtoResponse> findAll(Pageable pageable);

    Page<StudentDtoResponse>  findAllByTeacher(Long teacherId, Pageable pageable);

}
