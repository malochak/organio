package organio.error.exception;

import lombok.Getter;
import org.springframework.core.NestedRuntimeException;

public class UserException extends NestedRuntimeException {

    @Getter
    private final String rejectedValue;

    public UserException(String msg, String rejectedValue) {
        super(msg);
        this.rejectedValue = rejectedValue;
    }
}
