package dev.malkon.events.error.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;

@Data
public class RequestError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<RequestSubError> subErrors;

    public RequestError(HttpStatus status, Throwable throwable) {
        this(status, throwable, emptyList());
    }

    public RequestError(HttpStatus status, Throwable throwable, List<RequestSubError> subErrors) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = throwable.getMessage();
        this.debugMessage = throwable.getLocalizedMessage();
        this.subErrors = subErrors;
    }

    public ResponseEntity<RequestError> asResponseEntity() {
        return new ResponseEntity<>(this, status);
    }
}
