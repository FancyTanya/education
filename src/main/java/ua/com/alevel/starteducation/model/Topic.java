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
@Table(name = "topics")
public class Topic extends BaseEntity{

    @Column
    private String name;

    @OneToMany(mappedBy = "topic")
    private List<Lesson> lessons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
