package pl.fis.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.fis.restapi.entity.Teacher;
import pl.fis.restapi.enums.Subject;
import pl.fis.restapi.service.StudentService;
import pl.fis.restapi.service.TeacherService;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @BeforeEach
    private void addStudentsAndTeachers() {
        studentService.getAllStudents().clear();
        studentService.addStudents();
        teacherService.getAllTeachers().clear();
        teacherService.addTeachers();
    }

    @Test
    public void getAllTeachersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.[0].subject", Is.is(Subject.BIOLOGY.toString())))
                .andExpect(jsonPath("$.[1].subject", Is.is(Subject.ALGEBRA.toString())))
                .andExpect(jsonPath("$.[2].subject", Is.is(Subject.LAW.toString())));
    }

    @ParameterizedTest
    @CsvSource({"1, 5", "2, 4", "3, 3"})
    public void getTeachersCLassTest(Long id, Integer expectedClassSize) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}/class", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(expectedClassSize)));
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "2, 2", "3, 3"})
    public void deleteStudentFromClassByTeacherIdTest(Long teacherId, Long studentId) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/deleteStudentFromClass/{teacherId}",
                teacherId).param("studentId", String.valueOf(studentId)))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.attendedSubjects", Matchers.empty()));
    }

    @ParameterizedTest
    @CsvSource({"1, BIOLOGY", "2, ALGEBRA", "3, LAW"})
    public void getTeacherByIdTest(Long id, String subject) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subject", Matchers.equalTo(subject)));
    }

    @Test
    public void addTeacherTest() throws Exception {
        teacherService.deleteTeacher(1L);
        Teacher teacher = new Teacher("Test", "TestLastName", Subject.BIOLOGY);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher")
                .content(mapper.writeValueAsString(teacher))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}", teacherService
                        .getAllTeachers()
                        .get(teacherService.getAllTeachers().size() - 1).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("Test")))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo("TestLastName")))
                .andExpect(jsonPath("$.subject", Matchers.equalTo(Subject.BIOLOGY.toString())));
    }

//TODO
//    @Test
//    public void addTeacherWithExistingSubjectTest() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(TeacherController.class)
//                .setControllerAdvice(new TeacherControllerAdvice())
//                .build();
//        Teacher teacher = new Teacher("Anna", "Test", Subject.BIOLOGY);
//        ObjectMapper mapper = new ObjectMapper();
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher")
//                        .content(mapper.writeValueAsString(teacher))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException))
//                .andExpect(result -> assertEquals(String.format("Teacher with subject %s already exists", teacher.getSubject()),
//                        result.getResolvedException().getMessage()));
//    }

    @Test
    public void editTeacherTest() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setFirstName("TestFirstName");
        teacher.setLastName("TestLastName");
        long id = 1;
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/teacher/{id}", id)
                        .content(mapper.writeValueAsString(teacher))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", Is.is("TestFirstName")))
                .andExpect(jsonPath("$.lastName", Is.is("TestLastName")));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    public void deleteTeacherTest(Long id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/{id}",
                        id)).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
