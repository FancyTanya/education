package ua.com.alevel.starteducation.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.starteducation.dto.request.StudentDtoRequest;
import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.StudentDtoResponse;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.service.StudentService;
import ua.com.alevel.starteducation.service.TeacherService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Service
public class StudentTest {

    @Autowired
    private StudentService studentService;

    public StudentDtoResponse createStudent(String fistName,
                                            String lastName,
                                            String email,
                                            String password) {
        StudentDtoRequest studentDtoRequest = new StudentDtoRequest();
        studentDtoRequest.setFirstName(fistName);
        studentDtoRequest.setFirstName(lastName);
        studentDtoRequest.setEmail(email);
        studentDtoRequest.setEmail(password);

        StudentDtoResponse dtoResponse = studentService.create(studentDtoRequest);

        assertNotNull(dtoResponse.getId(), "Student id must not be null!");
        assertEquals(email, dtoResponse.getEmail(), "Student's email update isn't applied!");

        return dtoResponse;
    }

    public StudentDtoResponse createTeacher(String fistName,
                                            String lastName,
                                            String password) {
        return createStudent(fistName, lastName, null, password);
    }

    public StudentDtoResponse createTeacher(String email,
                                            String password) {
        return createStudent(null, null, email, password);
    }

    public void deleteStudent(Long id) {
        studentService.delete(id);
    }
}
