package ua.com.alevel.starteducation.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class OverrideUserPasswordRequest {

    @NotBlank(message = "password must not be blank")
    @Size(min = 8, message = "password's length must be at least 8")
    private String password;
}
