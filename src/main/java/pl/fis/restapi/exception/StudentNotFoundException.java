package pl.fis.restapi.exception;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
