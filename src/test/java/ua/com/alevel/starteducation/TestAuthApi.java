package ua.com.alevel.starteducation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.com.alevel.starteducation.dto.request.SaveUserRequest;
import ua.com.alevel.starteducation.dto.response.UserDtoResponse;
import ua.com.alevel.starteducation.model.auth.EducationUserTest;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.com.alevel.starteducation.util.JsonHelper.fromJson;
import static ua.com.alevel.starteducation.util.JsonHelper.toJson;

@SpringBootTest
@AutoConfigureMockMvc
public class TestAuthApi {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final EducationUserTest educationUserTest;

    private final String password = "Test12345_";

    @Autowired
    public TestAuthApi(MockMvc mockMvc, ObjectMapper objectMapper, EducationUserTest educationUserTest) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.educationUserTest = educationUserTest;
    }

    @Test
    void testRegisterSuccess() throws Exception {
        SaveUserRequest goodRequest = new SaveUserRequest();
        goodRequest.setEmail("test user");
        goodRequest.setPassword(password);

        MvcResult mvcResult = this.mockMvc
                .perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, goodRequest)))
                .andExpect(status().isOk())
                .andReturn();

        UserDtoResponse dtoResponse = fromJson(objectMapper, mvcResult.getResponse().getContentAsString(), UserDtoResponse.class);
        assertNotNull(dtoResponse.getId(), "User id must not be null!");
        assertEquals(goodRequest.getEmail(), dtoResponse.getEmail(), "User email  update isn't applied!");
    }

    @Test
    void testRegisterFail() throws Exception {
        SaveUserRequest badRequest = new SaveUserRequest();
        badRequest.setEmail("invalid.email");

        this.mockMvc
                .perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Method argument validation failed")));
    }
}
