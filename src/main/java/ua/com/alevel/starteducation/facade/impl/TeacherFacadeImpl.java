package ua.com.alevel.starteducation.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.facade.TeacherFacade;
import ua.com.alevel.starteducation.model.Teacher;
import ua.com.alevel.starteducation.service.TeacherService;

@Service
public class TeacherFacadeImpl implements TeacherFacade {

    private final TeacherService teacherService;

    public TeacherFacadeImpl(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public void create(TeacherDtoRequest dto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacherService.create(teacher);
    }

    @Override
    public void update(TeacherDtoRequest dto, Long id) {
        Teacher teacher = teacherService.findById(id);
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacherService.update(teacher);
    }

    @Override
    public void delete(Long id) {
        if (teacherService.existById(id)) {
            teacherService.delete(id);
        }
    }

    @Override
    public TeacherDtoResponse findById(Long id) {
        return new TeacherDtoResponse(teacherService.findById(id));
    }

    @Override
    public Page<TeacherDtoResponse> findAll(Pageable pageable) {
        return teacherService.findAll(pageable);
    }
}

