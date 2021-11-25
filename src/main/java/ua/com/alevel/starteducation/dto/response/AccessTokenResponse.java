package ua.com.alevel.starteducation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AccessTokenResponse {

    private String accessToken;

    private String refreshToken;

    private Long expireIn;
}
