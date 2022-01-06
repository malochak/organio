package organio.error.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import organio.error.domain.RequestError;
import organio.error.domain.RequestSubError;
import organio.error.domain.ValidationError;
import organio.error.exception.InvalidRequestBodyException;
import organio.error.exception.RecordCreationException;
import organio.error.exception.RecordNotFoundException;
import organio.error.exception.UserException;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestBodyException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public RequestError handleIncorrectRequestBody(InvalidRequestBodyException exception) {
        return new RequestError(BAD_REQUEST, exception, exception.getErrorList());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    public RequestError handleRecordNotFound(RecordNotFoundException exception) {
        return new RequestError(NOT_FOUND, exception);
    }

    @ExceptionHandler(RecordCreationException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public RequestError handleRecordNotFound(RecordCreationException exception) {
        return new RequestError(BAD_REQUEST, exception);
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    @ResponseStatus(CONFLICT)
    public RequestError handleUserException(UserException exception) {
        List<RequestSubError> error = List.of(new ValidationError(
                EMPTY,
                "login",
                exception.getRejectedValue(),
                exception.getMessage())
        );
        return new RequestError(BAD_REQUEST, exception, error);
    }
}
