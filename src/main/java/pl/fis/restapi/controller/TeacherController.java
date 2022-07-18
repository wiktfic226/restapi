package pl.fis.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.fis.restapi.entity.Student;
import pl.fis.restapi.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping(path = "api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(path = "/{id}/class")
    @ResponseBody
    public List<Student> getTeacherClass(@PathVariable Long id) {
        return teacherService.getTeacherClass(id);
    }
}
