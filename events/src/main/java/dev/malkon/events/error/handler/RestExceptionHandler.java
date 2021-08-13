package dev.malkon.events.error.handler;

import dev.malkon.events.error.domain.RequestError;
import dev.malkon.events.error.exception.InvalidRequestBodyException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<RequestError> handleIncorrectRequestBody(InvalidRequestBodyException exception) {
        return new RequestError(BAD_REQUEST, exception, exception.getErrorList()).asResponseEntity();
    }

}
