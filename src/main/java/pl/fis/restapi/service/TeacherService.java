package pl.fis.restapi.service;

import org.springframework.stereotype.Service;
import pl.fis.restapi.entity.Student;
import pl.fis.restapi.entity.Teacher;
import pl.fis.restapi.enums.Subject;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private List<Teacher> teachers;

    private final StudentService studentService;

    public TeacherService(StudentService studentService) {
        this.teachers = new ArrayList<>();
        this.studentService = studentService;
    }

    public void addTeacher(Teacher teacher) {
        teacher.setId((long) (teachers.size() + 1));
        for(Teacher teacher1 : teachers) {
            if(teacher1.getSubject() == teacher.getSubject())
                throw new IllegalArgumentException(
                        String.format("Teacher with subject %s already exists",
                                teacher.getSubject()));
        }
        teachers.add(teacher);
    }

    public Teacher getTeacherById(Long id) {
        List<Teacher> foundTeachers = teachers
                .stream()
                .filter(teacher -> teacher.getId().equals(id))
                .collect(Collectors.toList());
        if(foundTeachers.size() != 0)
            return foundTeachers.get(0);
        else
            throw new IllegalArgumentException(String.format("Teacher with id %d does not exists", id));
    }

    public List<Teacher> getAllTeachers() {
        return teachers;
    }

    public void editTeacher(Long id, Teacher toUpdate) {
        List<Teacher> foundTeachers = teachers
                .stream()
                .filter(teacher -> teacher.getId().equals(id))
                .collect(Collectors.toList());
        if(foundTeachers.size() == 0)
            throw new IllegalArgumentException(String.format("Teacher with id %d does not exists", id));
        if(toUpdate.getFirstName() != null && toUpdate.getLastName() != null && toUpdate.getSubject() != null) {
            foundTeachers.get(0).setFirstName(toUpdate.getFirstName());
            foundTeachers.get(0).setLastName(toUpdate.getLastName());
            foundTeachers.get(0).setSubject(toUpdate.getSubject());
        }
    }

    public List<Student> getTeacherClass(Long id) {
        List<Teacher> foundTeachers = teachers
                .stream()
                .filter(teacher -> teacher.getId().equals(id))
                .collect(Collectors.toList());
        if(foundTeachers.size() == 0)
            throw new IllegalArgumentException(String.format("Teacher with id %d does not exists", id));
        return studentService
                .getAllStudents()
                .stream()
                .filter(student -> student.getAttendedSubjects().contains(foundTeachers.get(0).getSubject()))
                .collect(Collectors.toList());
    }

    @PostConstruct
    private void addTeachers() {
        Teacher teacher1 = new Teacher(1L, "Anna", "Biologia", Subject.BIOLOGY);
        Teacher teacher2 = new Teacher(2L, "Karolina", "Algebra", Subject.ALGEBRA);
        Teacher teacher3 = new Teacher(3L, "Katarzyna", "Prawo", Subject.LAW);
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
    }
}
