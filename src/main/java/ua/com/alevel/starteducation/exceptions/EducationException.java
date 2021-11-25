package ua.com.alevel.starteducation.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@NoArgsConstructor
public class EducationException {

    public static ResponseStatusException authorityNotFound(String value) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User authority " + value + " not defined");
    }

    public static ResponseStatusException userNotFound(String email) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email " + email + " not found");
    }

    public static ResponseStatusException userNotFound(long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
    }


    public static ResponseStatusException duplicateEmail(String email) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email " + email + " already taken");
    }

    public static ResponseStatusException invalidRefreshToken(InvalidRefreshTokenException cause) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                "Refresh token is invalid! It may have been rotated, invalidated or expired naturally", cause);
    }

    public static ResponseStatusException wrongPassword() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
    }
}
