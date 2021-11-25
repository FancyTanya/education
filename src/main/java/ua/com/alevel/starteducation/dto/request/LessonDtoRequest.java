package ua.com.alevel.starteducation.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class LessonDtoRequest {

    private String title;

    private OffsetDateTime date;

    private boolean isComplete;
}
