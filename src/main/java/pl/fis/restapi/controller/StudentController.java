package pl.fis.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.fis.restapi.entity.Student;
import pl.fis.restapi.service.StudentService;

import java.util.List;

@Controller
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping(path = "/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Student toUpdate) {
        studentService.editStudent(id, toUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
