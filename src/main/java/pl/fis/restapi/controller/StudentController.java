package pl.fis.restapi.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fis.restapi.entity.Student;
import pl.fis.restapi.service.StudentService;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Rest API app", description = "Spring app created by some intern"))
@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().header("Successful", "true").body(studentService.getAllStudents());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok().header("Successful", "true").body(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.ok().header("Successful", "true").build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long id, @RequestBody Student toUpdate) {
        studentService.editStudent(id, toUpdate);
        return ResponseEntity.ok().header("Successful", "true").build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().header("Successful", "true").build();
    }
}
