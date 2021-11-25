package ua.com.alevel.starteducation.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.starteducation.dto.request.LessonDtoRequest;
import ua.com.alevel.starteducation.dto.response.LessonDtoResponse;
import ua.com.alevel.starteducation.facade.LessonFacade;
import ua.com.alevel.starteducation.model.Lesson;
import ua.com.alevel.starteducation.model.Teacher;
import ua.com.alevel.starteducation.service.LessonService;
import ua.com.alevel.starteducation.service.TeacherService;

@Service
@Transactional
public class LessonFacadeImpl implements LessonFacade {

    private final LessonService lessonService;
    private final TeacherService teacherService;

    public LessonFacadeImpl(LessonService lessonService, TeacherService teacherService) {
        this.lessonService = lessonService;
        this.teacherService = teacherService;
    }

    @Override
    @Transactional
    public void create(LessonDtoRequest dto, Long teacherId) {
        Lesson lesson = new Lesson();
        lesson.setTitle(dto.getTitle());
        Teacher teacher = teacherService.findById(teacherId);
        lesson.setTeacher(teacher);
        lessonService.create(lesson);
    }

    @Override
    public void update(LessonDtoRequest dto, Long id) {
        Lesson lesson = lessonService.findById(id);
        lesson.setTitle(dto.getTitle());
        lessonService.update(lesson);
    }

    @Override
    public void delete(Long id) {
       if(lessonService.existById(id)) {
           lessonService.delete(id);
       }
    }

    @Override
    public LessonDtoResponse findById(Long id) {
        return new LessonDtoResponse(lessonService.findById(id));
    }

    @Override
    public Page<LessonDtoResponse> findAll(Pageable pageable) {
        return lessonService.findAll(pageable);
    }

    @Override
    public Page<LessonDtoResponse> findAllByTeacher(Long teacherId, Pageable pageable) {
        Teacher teacher = teacherService.findById(teacherId);
        return lessonService.findAllByTeacher(teacherId, pageable);
    }
}
