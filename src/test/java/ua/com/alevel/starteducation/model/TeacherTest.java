package ua.com.alevel.starteducation.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.service.TeacherService;

import static org.junit.jupiter.api.Assertions.*;

@Service
public class TeacherTest {

    @Autowired
    private TeacherService teacherService;

    public TeacherDtoResponse createTeacher(String fistName,
                                            String lastName,
                                            String email,
                                            String password) {
        TeacherDtoRequest teacherDtoRequest = new TeacherDtoRequest();
        teacherDtoRequest.setFirstName(fistName);
        teacherDtoRequest.setFirstName(lastName);
        teacherDtoRequest.setEmail(email);
        teacherDtoRequest.setEmail(password);

        TeacherDtoResponse dtoResponse = teacherService.createTeacher(teacherDtoRequest);

        assertNotNull(dtoResponse.getId(), "Teacher id must not be null!");
        assertEquals(email, dtoResponse.getEmail(), "Teacher's email update isn't applied!");

        return dtoResponse;
    }

    public TeacherDtoResponse createTeacher(String fistName,
                                            String lastName,
                                            String password) {
        return createTeacher(fistName, lastName, null, password);
    }

    public TeacherDtoResponse createTeacher(String email,
                                            String password) {
        return createTeacher(null, null, email, password);
    }

    public void deleteTeacher(Long id) {
        teacherService.delete(id);
    }
}