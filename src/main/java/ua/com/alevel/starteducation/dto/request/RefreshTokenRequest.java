package ua.com.alevel.starteducation.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RefreshTokenRequest {

    @NotNull
    private String refreshToken;
}
