package ua.com.alevel.starteducation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity{

    @Column
    private String title;

    @Column(name = "date")
    private OffsetDateTime date;

    @Column
    private boolean isComplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lesson")
    private List<Grade> grades;
}
