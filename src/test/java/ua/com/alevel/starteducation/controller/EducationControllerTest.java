//package ua.com.alevel.starteducation.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ua.com.alevel.starteducation.dto.request.SaveUserRequest;
//import ua.com.alevel.starteducation.service.UserService;
//
//import static org.mockito.Mockito.*;
//
//public class EducationControllerTest {
//
//    private UserService userService;
//    private UserController userController;
//    private SaveUserRequest saveUserRequest;
//
//    @BeforeEach
//    void setUp() {
//        userService = mock(UserService.class);
//        userController = new UserController(userService);
//    }
//
//    @Test
//    void shouldRegisterNewUser() {
//        userService.create(saveUserRequest);
//        verify(userService, only()).create(saveUserRequest);
//    }
//}
