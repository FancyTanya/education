package ua.com.alevel.starteducation.service.impl;

import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.response.LessonDtoResponse;
import ua.com.alevel.starteducation.dto.response.ResponseContainer;
import ua.com.alevel.starteducation.exceptions.EducationException;
import ua.com.alevel.starteducation.model.Lesson;
import ua.com.alevel.starteducation.repository.LessonRepository;
import ua.com.alevel.starteducation.repository.TeacherRepository;
import ua.com.alevel.starteducation.service.LessonService;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository, TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void create(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Override
    public void update(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return lessonRepository.existsById(id);
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> EducationException.userNotFound(id));
    }

    @Override
    @PageableAsQueryParam
    public Page<Lesson> findAll(@Parameter(hidden = true) Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }

    @Override
    @PageableAsQueryParam
    public Page<LessonDtoResponse> findAllByTeacher(Long teacherId, @Parameter(hidden = true) Pageable pageable) {
        return lessonRepository.findAllByTeacher(teacherId, pageable);
    }
}
