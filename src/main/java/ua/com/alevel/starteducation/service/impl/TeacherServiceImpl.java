package ua.com.alevel.starteducation.service.impl;

import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.exceptions.EducationException;
import ua.com.alevel.starteducation.model.Teacher;
import ua.com.alevel.starteducation.repository.TeacherRepository;
import ua.com.alevel.starteducation.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void create(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return teacherRepository.existsById(id);
    }

    @Override
    public Teacher findById(Long id) {
       return teacherRepository.findById(id).orElseThrow(() -> EducationException.userNotFound(id));
    }

    @Override
    @PageableAsQueryParam
    public Page<TeacherDtoResponse> findAll(@Parameter(hidden = true) Pageable pageable) {
        return teacherRepository.findAll(pageable).map(TeacherDtoResponse::fromTeacher);

    }

}
