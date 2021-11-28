package ua.com.alevel.starteducation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.starteducation.dto.response.ResponseContainer;
import ua.com.alevel.starteducation.dto.response.StudentDtoResponse;
import ua.com.alevel.starteducation.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<StudentDtoResponse> findAllByTeacher(Long teacherId, Pageable pageable);

    boolean existsByEmail(String email);
}
