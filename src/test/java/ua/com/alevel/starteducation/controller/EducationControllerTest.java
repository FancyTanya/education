package ua.com.alevel.starteducation.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.starteducation.dto.request.ChangeUserPasswordRequest;
import ua.com.alevel.starteducation.dto.request.SaveUserRequest;
import ua.com.alevel.starteducation.service.UserService;

import static org.mockito.Mockito.*;

public class EducationControllerTest {

    private UserService userService;
    private UserController userController;
    private SaveUserRequest saveUserRequest;
    private ChangeUserPasswordRequest request;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void shouldRegisterNewUser() {
        userService.create(saveUserRequest);
        verify(userService, only()).create(saveUserRequest);
    }

    @Test
    void shouldDeleteCurrentUser() {
        userService.deleteByEmail("email");
        verify(userService, only()).deleteByEmail("email");
    }

    @Test
    void shouldChangeUserPassword() {
        userService.changePasswordByEmail("email", request);
        verify(userService, only()).changePasswordByEmail("email", request);
    }
}
