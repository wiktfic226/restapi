package pl.fis.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NotFoundException extends HttpStatusCodeException {
    protected NotFoundException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
