package ua.com.alevel.starteducation.model.auth;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Entity
@EqualsAndHashCode
@Table(name = "authorities")
public class EducationUserAuthority {

    @Id
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private KnownAuthority id;

    @ManyToMany(mappedBy = "authorities")
    private Set<EducationUser> users = new HashSet<>();
}
