package ua.com.alevel.starteducation.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.starteducation.dto.request.TopicDtoRequest;
import ua.com.alevel.starteducation.dto.response.TopicDtoResponse;

public interface TopicFacade {

    void create(TopicDtoRequest dto, Long id);

    void update(TopicDtoRequest dto, Long id);

    void delete(Long id);

    TopicDtoResponse findById(Long id);

    Page<TopicDtoResponse> findAll(Pageable pageable);

    Page<TopicDtoResponse> findAllByTeacher(Long teacherId, Pageable pageable);
}
