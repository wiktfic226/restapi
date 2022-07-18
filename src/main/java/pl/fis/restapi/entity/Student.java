package pl.fis.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fis.restapi.enums.Subject;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<Subject> attendedSubjects;

    public Student(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Student(String firstName, String lastName, Integer age, List<Subject> attendedSubjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.attendedSubjects = attendedSubjects;
    }
}
