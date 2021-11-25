package ua.com.alevel.starteducation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.starteducation.dto.response.LessonDtoResponse;
import ua.com.alevel.starteducation.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Page<LessonDtoResponse> findAllByTeacher(Long teacherId, Pageable pageable);
}
