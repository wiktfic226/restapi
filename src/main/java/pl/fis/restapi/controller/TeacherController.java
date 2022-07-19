package pl.fis.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fis.restapi.entity.Student;
import pl.fis.restapi.entity.Teacher;
import pl.fis.restapi.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping(path = "api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(path = "/{id}/class")
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<List<Student>> getTeacherClass(@PathVariable Long id) {
        return ResponseEntity.ok().header("Successful", "true").body(teacherService.getTeacherClass(id));
    }

    @DeleteMapping(path = "/deleteStudentFromClass/{teacherId}")
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Void> deleteStudentFromClassByTeacherId(@PathVariable Long teacherId, @RequestParam Long studentId) {
        teacherService.deleteStudentFromClassByTeacherId(teacherId, studentId);
        return ResponseEntity.ok().header("Successful", "true").build();
    }

    @GetMapping
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok().header("Successful", "true").body(teacherService.getAllTeachers());
    }

    @GetMapping(path = "/{id}")
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok().header("Successful", "true").body(teacherService.getTeacherById(id));
    }

    @PostMapping
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Void> addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return ResponseEntity.ok().header("Successful", "true").build();
    }

    @PutMapping(path = "/{id}")
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Void> editTeacher(@PathVariable Long id, @RequestBody Teacher toUpdate) {
        teacherService.editTeacher(id, toUpdate);
        return ResponseEntity.ok().header("Successful", "true").build();
    }

    @DeleteMapping(path = "/{id}")
    @Operation(parameters = @Parameter(in = ParameterIn.HEADER, name = "role"))
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().header("Successful", "true").build();
    }
}
