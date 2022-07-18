package pl.fis.restapi.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.fis.restapi.exception.InvalidDataException;
import pl.fis.restapi.exception.NotFoundException;
import pl.fis.restapi.exception.StudentNotFoundException;
import pl.fis.restapi.exception.TeacherNotFoundException;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({StudentNotFoundException.class, TeacherNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header("Successful", "false")
                .body(exception.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidDataException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("Successful", "false")
                .body(exception.getMessage());
    }
}
