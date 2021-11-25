package ua.com.alevel.starteducation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.starteducation.dto.response.LessonDtoResponse;
import ua.com.alevel.starteducation.model.Lesson;

public interface LessonService extends CrudService<Lesson>{
    Page<LessonDtoResponse> findAllByTeacher(Long teacherId, Pageable pageable);
}
