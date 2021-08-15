package dev.malkon.events.error.exception;

import dev.malkon.events.error.domain.RequestSubError;
import org.springframework.core.NestedRuntimeException;

import java.util.List;

import static java.util.Collections.emptyList;

public class InvalidRequestBodyException extends NestedRuntimeException {

    private final List<RequestSubError> errorList;

    public InvalidRequestBodyException(String message, List<RequestSubError> errorList) {
        super(message);
        this.errorList = errorList;
    }

    public InvalidRequestBodyException(String message) {
        this(message, emptyList());
    }

    public List<RequestSubError> getErrorList() {
        return errorList;
    }
}
