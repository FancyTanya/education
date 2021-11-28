package ua.com.alevel.starteducation.model.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.request.SaveUserRequest;
import ua.com.alevel.starteducation.dto.response.UserDtoResponse;
import ua.com.alevel.starteducation.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Service
public class EducationUserTest {

    @Autowired
    private UserService userService;

    public UserDtoResponse createUser(String email,
                                      String password) {
        SaveUserRequest userRequest = new SaveUserRequest();
        userRequest.setEmail(email);
        userRequest.setPassword(password);

        UserDtoResponse userDtoResponse = userService.create(userRequest);

        assertNotNull(userDtoResponse.getId(), "User id must not be null!");
        assertEquals(email, userDtoResponse.getEmail(), "User's email update isn't applied!");

        return userDtoResponse;
    }

    public void deleteUser(String email) {
        userService.deleteByEmail(email);
    }
}