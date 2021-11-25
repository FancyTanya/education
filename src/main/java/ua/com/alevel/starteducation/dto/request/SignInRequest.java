package ua.com.alevel.starteducation.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

    @JsonAlias({"username", "email"})
    private String login;
    private String password;
}
