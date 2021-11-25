package ua.com.alevel.starteducation.service.impl;

import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.response.GradeDtoResponse;
import ua.com.alevel.starteducation.dto.response.ResponseContainer;
import ua.com.alevel.starteducation.exceptions.EducationException;
import ua.com.alevel.starteducation.model.Grade;
import ua.com.alevel.starteducation.repository.GradeRepository;
import ua.com.alevel.starteducation.service.GradeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public void create(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    public void update(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    public void delete(Long id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return gradeRepository.existsById(id);
    }

    @Override
    public Grade findById(Long id) {
        return gradeRepository.findById(id).orElseThrow(() -> EducationException.userNotFound(id));
    }

    @Override
    @PageableAsQueryParam
    public Page<GradeDtoResponse> findAll(@Parameter(hidden = true) Pageable pageable) {
        return gradeRepository.findAll(pageable).map(GradeDtoResponse::fromGrade);
    }

    @Override
    @PageableAsQueryParam
    public Page<GradeDtoResponse> findAllByStudent(Long studentId, @Parameter(hidden = true) Pageable pageable) {
        return gradeRepository.findAllByStudent(studentId, pageable);
    }
}
