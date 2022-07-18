package pl.fis.restapi.controller;

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
    public List<Student> getTeacherClass(@PathVariable Long id) {
        return teacherService.getTeacherClass(id);
    }

    @DeleteMapping(path = "/deleteStudentFromClass/{teacherId}")
    public void deleteStudentFromClassByTeacherId(@PathVariable Long teacherId, @RequestParam Long studentId) {
        teacherService.deleteStudentFromClassByTeacherId(teacherId, studentId);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(path = "/{id}")
    public Teacher getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    @PutMapping(path = "/{id}")
    public void editTeacher(@PathVariable Long id, @RequestBody Teacher toUpdate) {
        teacherService.editTeacher(id, toUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
}
