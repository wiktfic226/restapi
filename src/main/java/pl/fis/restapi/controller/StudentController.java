package pl.fis.restapi.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
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
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().header("Successful", "true").body(studentService.getAllStudents());
    }

    @GetMapping(path = "/{id}")
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok().header("Successful", "true").body(studentService.getStudentById(id));
    }

    @PostMapping
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.ok().header("Successful", "true").build();
    }

    @PutMapping(path = "/{id}")
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Void> updateStudent(@PathVariable Long id, @RequestBody Student toUpdate) {
        studentService.editStudent(id, toUpdate);
        return ResponseEntity.ok().header("Successful", "true").build();
    }

    @DeleteMapping(path = "/{id}")
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().header("Successful", "true").build();
    }
}
