package ua.com.alevel.starteducation.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.starteducation.dto.request.TopicDtoRequest;
import ua.com.alevel.starteducation.dto.response.TopicDtoResponse;
import ua.com.alevel.starteducation.facade.TopicFacade;
import ua.com.alevel.starteducation.model.Teacher;
import ua.com.alevel.starteducation.model.Topic;
import ua.com.alevel.starteducation.service.TeacherService;
import ua.com.alevel.starteducation.service.TopicService;

@Service
@Transactional
public class TopicFacadeImpl implements TopicFacade {

    private final TopicService topicService;
    private final TeacherService teacherService;

    public TopicFacadeImpl(TopicService topicService, TeacherService teacherService) {
        this.topicService = topicService;
        this.teacherService = teacherService;
    }

    @Override
    @Transactional
    public void create(TopicDtoRequest dto, Long teacherId) {
        Topic topic = new Topic();
        topic.setName(dto.getName());
        Teacher teacher = teacherService.findById(teacherId);
        topic.setTeacher(teacher);
        topicService.create(topic);
    }

    @Override
    public void update(TopicDtoRequest dto, Long id) {
        Topic topic = topicService.findById(id);
        topic.setName(dto.getName());
        topicService.update(topic);
    }

    @Override
    public void delete(Long id) {
        if (topicService.existById(id)) {
            topicService.delete(id);
        }
    }

    @Override
    public TopicDtoResponse findById(Long id) {
        return new TopicDtoResponse(topicService.findById(id));
    }

    @Override
    public Page<TopicDtoResponse> findAll(Pageable pageable) {
        return topicService.findAll(pageable);
    }

    @Override
    public Page<TopicDtoResponse> findAllByTeacher(Long teacherId, Pageable pageable) {
        return topicService.findAllByTeacher(teacherId, pageable);
    }
}
