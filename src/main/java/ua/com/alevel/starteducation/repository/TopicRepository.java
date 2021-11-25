package ua.com.alevel.starteducation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.starteducation.dto.response.TopicDtoResponse;
import ua.com.alevel.starteducation.model.Teacher;
import ua.com.alevel.starteducation.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<TopicDtoResponse> findAllByTeacher(Teacher teacher, Pageable pageable);
}
