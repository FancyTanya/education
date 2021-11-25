package ua.com.alevel.starteducation.service.impl;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.response.TopicDtoResponse;
import ua.com.alevel.starteducation.model.Topic;
import ua.com.alevel.starteducation.repository.TeacherRepository;
import ua.com.alevel.starteducation.repository.TopicRepository;
import ua.com.alevel.starteducation.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TeacherRepository teacherRepository;

    public TopicServiceImpl(TopicRepository topicRepository, TeacherRepository teacherRepository) {
        this.topicRepository = topicRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void create(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void update(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void delete(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return topicRepository.existsById(id);
    }

    @Override
    public Topic findById(Long id) {
        return topicRepository.getById(id);
    }

    @Override
    public Page<Topic> findAll(@Parameter(hidden = true) Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public Page<TopicDtoResponse> findAllByTeacher(Long teacherId, @Parameter(hidden = true) Pageable pageable) {
        return topicRepository.findAllByTeacher(teacherRepository.getById(teacherId), pageable);
    }
}
