package ua.com.alevel.starteducation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "groups")
public class Group extends BaseEntity{

    @Column
    private String name;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    @OneToMany(mappedBy = "group")
    private List<Lesson> lessons;

}
