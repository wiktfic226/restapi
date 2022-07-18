package pl.fis.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fis.restapi.enums.Subject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Long id;
    private String firstName;
    private String lastName;
    private Subject subject;

    public Teacher(String firstName, String lastName, Subject subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }
}
