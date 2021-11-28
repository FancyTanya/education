package ua.com.alevel.starteducation.dto.request;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.starteducation.model.Teacher;

@Getter
@Setter

public class TeacherDtoRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public static Teacher createTeacherFromRequest(TeacherDtoRequest request) {
        return new Teacher(request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword());
    }

}
