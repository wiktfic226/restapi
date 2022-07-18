package pl.fis.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import pl.fis.restapi.entity.Student;
import pl.fis.restapi.enums.Subject;
import pl.fis.restapi.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    StudentService studentService;

    @BeforeEach
    private void addStudents() {
        studentService.getAllStudents().clear();
        studentService.addStudents();
    }

    @Test
    public void getAllStudentsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @ParameterizedTest
    @CsvSource({"1, Wiktoria", "2, Aleksandra", "3, Ala"})
    public void getStudentByIdTest(Long id, String firstName) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName", Matchers.equalTo(firstName)));
    }

    @Test
    public void addStudentTest() throws Exception {
        Student student = new Student("Test", "TestLastName", 25, new ArrayList<>(List.of(Subject.BIOLOGY)));
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/student")
                .content(mapper.writeValueAsString(student))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/{id}", studentService
                        .getAllStudents()
                        .get(studentService.getAllStudents().size() - 1).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("Test")))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo("TestLastName")))
                .andExpect(jsonPath("$.age", Matchers.equalTo(25)));
    }

    @ParameterizedTest
    @CsvSource({"1, Wiktoria", "2, Aleksandra", "3, Ala"})
    public void updateStudentTest(Long id, String firstName) throws Exception{
        Student student = new Student("Test", "TestLastName", 25);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/student/{id}", id)
                        .content(mapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName", Matchers.equalTo(firstName)))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo("TestLastName")))
                .andExpect(jsonPath("$.age", Matchers.equalTo(25)));
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 4, 7})
    public void deleteStudentTest(Long id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/student/{id}",
                id)).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(9)));
    }
}
