package ua.com.alevel.starteducation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.starteducation.model.Topic;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopicDtoResponse {

    private String name;
    private Long teacherId;

    public TopicDtoResponse (Topic topic) {
        this.name = topic.getName();
        this.teacherId = topic.getTeacher().getId();
    }

    public static TopicDtoResponse fromTopic(Topic topic) {
        return new TopicDtoResponse(
                topic.getName(),
                topic.getTeacher().getId());
    }
}
