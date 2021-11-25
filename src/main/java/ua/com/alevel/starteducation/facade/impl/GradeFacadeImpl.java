package ua.com.alevel.starteducation.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.starteducation.dto.request.GradeDtoRequest;
import ua.com.alevel.starteducation.dto.response.GradeDtoResponse;
import ua.com.alevel.starteducation.facade.GradeFacade;
import ua.com.alevel.starteducation.model.Grade;
import ua.com.alevel.starteducation.model.Student;
import ua.com.alevel.starteducation.service.GradeService;
import ua.com.alevel.starteducation.service.StudentService;

@Component
@Transactional
public class GradeFacadeImpl implements GradeFacade {

    private final GradeService gradeService;
    private final StudentService studentService;

    public GradeFacadeImpl(GradeService gradeService, StudentService studentService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
    }

    @Override
    @Transactional
    public void create(GradeDtoRequest dto, Long studentId) {
        Grade grade = new Grade();
        grade.setGrade(dto.getGrade());
        Student student = studentService.findById(studentId);
        grade.setStudent(student);
        gradeService.create(grade);
    }

    @Override
    public void update(GradeDtoRequest dto, Long id) {
        Grade grade = gradeService.findById(id);
        grade.setGrade(dto.getGrade());
        gradeService.update(grade);
    }

    @Override
    public void delete(Long id) {
        if (gradeService.existById(id)) {
            gradeService.delete(id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GradeDtoResponse findById(Long id) {
        return new GradeDtoResponse(gradeService.findById(id));
    }

    @Override
    public Page<GradeDtoResponse> findAll(Pageable pageable) {
        return gradeService.findAll(pageable);
    }

    @Override
    public Page<GradeDtoResponse> findAllByStudent(Long id, Pageable pageable) {
        return gradeService.findAllByStudent(id, pageable);
    }


}
