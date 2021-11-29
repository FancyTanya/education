package ua.com.alevel.starteducation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.model.StudentTest;
import ua.com.alevel.starteducation.model.TeacherTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.com.alevel.starteducation.util.JsonHelper.fromJson;
import static ua.com.alevel.starteducation.util.JsonHelper.toJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class TestTeacherApi {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final TeacherTest teacherTest;
    private final StudentTest studentTest;

    @Autowired
    public TestTeacherApi(MockMvc mockMvc, ObjectMapper objectMapper, TeacherTest teacherTest, StudentTest studentTest) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.teacherTest = teacherTest;
        this.studentTest = studentTest;
    }

    @Test
    void testCreateSuccess()throws Exception {
        TeacherDtoRequest goodRequest =new TeacherDtoRequest();
        goodRequest.setFirstName("First");
        goodRequest.setLastName("Last");
        goodRequest.setEmail("email");

        MvcResult createResult = this.mockMvc
                .perform(post("/api/v1/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, goodRequest)))
                .andExpect(status().isOk())
                .andReturn();

        TeacherDtoResponse dtoResponse =fromJson(objectMapper, createResult.getResponse().getContentAsString(), TeacherDtoResponse.class);
        assertNotNull(dtoResponse.getId(), "Teacher id must not be null!");
        assertEquals(goodRequest.getEmail(), dtoResponse.getEmail(), "Teacher name update isn't applied!");
    }

    @Test
    void testCreateFail()throws Exception {
        TeacherDtoRequest badRequest = new TeacherDtoRequest();

        this.mockMvc
                .perform(post("/api/v1/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Method argument validation failed")));
    }
}
