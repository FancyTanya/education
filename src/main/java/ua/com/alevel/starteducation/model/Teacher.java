package ua.com.alevel.starteducation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import ua.com.alevel.starteducation.model.auth.EducationUserAuthority;
import ua.com.alevel.starteducation.model.auth.KnownAuthority;

import javax.persistence.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor


@Entity
@Table(name = "teachers")
public class Teacher extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NaturalId(mutable = true)
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "teacher")
    private List<Topic> topics;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons;

    public Teacher(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public KnownAuthority getRole() {
        return KnownAuthority.ROLE_TEACHER;
    }
}
