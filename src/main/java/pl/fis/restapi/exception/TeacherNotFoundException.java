package pl.fis.restapi.exception;

import org.springframework.http.HttpStatus;

public class TeacherNotFoundException extends NotFoundException {

    public TeacherNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
