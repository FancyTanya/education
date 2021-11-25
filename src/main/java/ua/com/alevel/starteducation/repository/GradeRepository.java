package ua.com.alevel.starteducation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.starteducation.dto.response.GradeDtoResponse;
import ua.com.alevel.starteducation.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Page<GradeDtoResponse> findAllByStudent(Long studentId, Pageable pageable);
}
