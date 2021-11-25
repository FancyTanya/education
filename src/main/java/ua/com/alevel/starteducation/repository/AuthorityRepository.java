package ua.com.alevel.starteducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.starteducation.model.auth.EducationUserAuthority;
import ua.com.alevel.starteducation.model.auth.KnownAuthority;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

public interface AuthorityRepository extends JpaRepository<EducationUserAuthority, KnownAuthority> {

    Set<KnownAuthority> TEACHERS_AUTHORITIES = EnumSet.of(KnownAuthority.ROLE_USER, KnownAuthority.ROLE_TEACHER);

    Set<KnownAuthority> ADMIN_AUTHORITIES = EnumSet.of(KnownAuthority.ROLE_USER, KnownAuthority.ROLE_ADMIN);

    Stream<EducationUserAuthority> findAllByIdIn(Set<KnownAuthority> ids);
}
