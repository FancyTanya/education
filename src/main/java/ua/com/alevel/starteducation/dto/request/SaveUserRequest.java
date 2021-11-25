package ua.com.alevel.starteducation.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SaveUserRequest {

    @Email(message = "email must be a valid email string")
    @NotNull(message = "email must not be null")
    private String email;

    @NotBlank(message = "password must not be blank")
    @Size(min = 8, message = "password's length must be at least 8")
    private String password;

}
