package ua.com.alevel.starteducation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.starteducation.dto.response.GradeDtoResponse;
import ua.com.alevel.starteducation.model.Grade;

public interface GradeService extends CrudService<Grade>{
    Page<GradeDtoResponse> findAllByStudent(Long studentId, Pageable pageable);
}
