package ua.com.alevel.starteducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.starteducation.model.auth.EducationUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EducationUser, Long>{

    Optional<EducationUser> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

}
