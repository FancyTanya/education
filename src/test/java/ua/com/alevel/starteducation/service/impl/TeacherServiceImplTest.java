//package ua.com.alevel.starteducation.service.impl;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.Mockito;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
//import ua.com.alevel.starteducation.model.Lesson;
//import ua.com.alevel.starteducation.model.Teacher;
//import ua.com.alevel.starteducation.model.Topic;
//import ua.com.alevel.starteducation.repository.LessonRepository;
//import ua.com.alevel.starteducation.repository.TeacherRepository;
//import ua.com.alevel.starteducation.repository.TopicRepository;
//import ua.com.alevel.starteducation.service.LessonService;
//import ua.com.alevel.starteducation.service.TeacherService;
//import ua.com.alevel.starteducation.service.TopicService;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class TeacherServiceImplTest {
//
//    private TeacherService teacherService;
//    private Page<Teacher> page;
//    private TeacherRepository teacherRepository;
//    private TopicService topicService;
//    private TopicRepository topicRepository;
//    private LessonRepository lessonRepository;
//    private LessonService lessonService;
//    private Topic topic;
//    private Lesson lesson;
//
//    @BeforeEach
//    void setUp() {
//        teacherRepository = mock(TeacherRepository.class);
//        teacherService = new TeacherServiceImpl(teacherRepository);
//        page = mock(Page.class);
//        Mockito.when(this.teacherRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(page);
//
//        topicRepository = mock(TopicRepository.class);
//        topicService = new TopicServiceImpl(topicRepository, teacherRepository);
//
//        lessonRepository = mock(LessonRepository.class);
//        lessonService = new LessonServiceImpl(lessonRepository, teacherRepository);
//
//        Teacher teacher = new Teacher();
//        teacher.setFirstName("First");
//        teacher.setLastName("Last");
//        teacher.setEmail("Email");
//        teacher.setPassword("Pass");
//        teacherService.create(teacher);
//
//
//        List<Topic> topics = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Topic topic = new Topic();
//            topic.setTeacher(teacher);
//            topic.setName("Topic");
//            topicService.create(topic);
//            topics.add(topic);
//        }
//
//        List<Lesson> lessons = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Lesson lesson = new Lesson();
//            lesson.setTeacher(teacher);
//            lesson.setTitle("title");
//            lesson.setTopic(topic);
//            lessonService.create(lesson);
//            lessons.add(lesson);
//        }
//
//    }
//
//    @Test
//    void create() {
////        Teacher teacher = new Teacher();
////        teacher.setFirstName("First");
////        teacher.setLastName("Last");
////        teacher.setEmail("Email");
////        teacher.setPassword("Pass");
////        teacherService.create(teacher);
//
//        Assertions.assertEquals(10, teacherService.findAll(page).getTotalElements());
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void findAll() {
//        when(teacherRepository.findAll()).thenReturn(Arrays.asList(
//                new Teacher("FirstName", "LastName", "email", "Password"),
//                new Teacher("Name", "Last", "Mail", "word")
//        ));
//        Pageable pageable = PageRequest.of(0, 2);
//        List<TeacherDtoResponse> responses = teacherService.findAll(pageable).getContent();
//
//        assertEquals(responses.size(), 2);
//
//        verify(teacherRepository).findAll();
//    }
//}