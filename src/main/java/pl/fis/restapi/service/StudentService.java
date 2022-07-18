package pl.fis.restapi.service;

import org.springframework.stereotype.Service;
import pl.fis.restapi.entity.Student;
import pl.fis.restapi.repository.StudentRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Student with id %d does not exists", id)));
    }

    public void editStudent(Long id, Student toUpdate) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Student with id %d does not exists", id)));
        if(toUpdate.getAge() != null && toUpdate.getLastName() != null) {
            student.setAge(toUpdate.getAge());
            student.setLastName(toUpdate.getLastName());
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @PostConstruct
    private void addStudents() {
        Student student1 = new Student("Wiktoria", "Fica", 22);
        Student student2 = new Student("Aleksandra", "Niefica", 25);
        Student student3 = new Student("Ala", "Makota", 21);
        Student student4 = new Student("Jan", "Kowalski", 19);
        Student student5 = new Student("Adam", "Nowak", 23);
    }
}
