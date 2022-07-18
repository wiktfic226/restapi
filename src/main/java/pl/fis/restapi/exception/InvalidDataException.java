package pl.fis.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class InvalidDataException extends HttpStatusCodeException {
    public InvalidDataException(String statusText) {
        super(HttpStatus.BAD_REQUEST, statusText);
    }
}
