package ua.com.alevel.starteducation.service;

import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.model.Teacher;

public interface TeacherService extends CrudService<Teacher> {

    TeacherDtoResponse createTeacher(TeacherDtoRequest request);

}
