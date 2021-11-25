package ua.com.alevel.starteducation.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.model.Teacher;

import java.util.List;

public interface TeacherFacade {

    void create(TeacherDtoRequest dto);

    void update(TeacherDtoRequest dto, Long id);

    void delete(Long id);

    TeacherDtoResponse findById(Long id);

    Page<TeacherDtoResponse> findAll(Pageable pageable);

}
