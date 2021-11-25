package ua.com.alevel.starteducation.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.starteducation.Routes;
import ua.com.alevel.starteducation.dto.request.ChangeUserPasswordRequest;
import ua.com.alevel.starteducation.dto.request.OverrideUserPasswordRequest;
import ua.com.alevel.starteducation.dto.request.SaveUserRequest;
import ua.com.alevel.starteducation.dto.response.UserDtoResponse;
import ua.com.alevel.starteducation.exceptions.EducationException;
import ua.com.alevel.starteducation.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.USERS)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoResponse register(@RequestBody @Valid SaveUserRequest request) {
        return userService.create(request);
    }

    @GetMapping("/me")
    public UserDtoResponse getCurrentUser(@AuthenticationPrincipal String email) {
        return userService.findByEmail(email).orElseThrow(() -> EducationException.userNotFound(email));
    }

    @PatchMapping("/me/password")
    public UserDtoResponse changeCurrentUserPassword(@AuthenticationPrincipal String email,
                                                     @RequestBody @Valid ChangeUserPasswordRequest request) {
        return userService.changePasswordByEmail(email, request);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurrentUser(@AuthenticationPrincipal String email) {
        userService.deleteByEmail(email);
    }

    @GetMapping("/{id}")
    public UserDtoResponse getUserById(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> EducationException.userNotFound(id));
    }

    @GetMapping
    @PageableAsQueryParam
    public Page<UserDtoResponse> listUsers(@Parameter(hidden = true) Pageable pageable) {
        return userService.list(pageable);
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoResponse registerAdmin(@RequestBody @Valid SaveUserRequest request) {
        return userService.createAdmin(request);
    }

    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoResponse registerTeacher(@RequestBody @Valid SaveUserRequest request) {
        return userService.createTeacher(request);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoResponse registerStudent(@RequestBody @Valid SaveUserRequest request) {
        return userService.createStudent(request);
    }

    @PatchMapping("/{id}/password")
    public UserDtoResponse changeUserPassword(@PathVariable long id,
                                              @RequestBody @Valid OverrideUserPasswordRequest request) {
        return userService.changePasswordById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable long id) {
        userService.deleteById(id);
    }
}
