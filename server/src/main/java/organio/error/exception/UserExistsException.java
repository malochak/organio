package organio.error.exception;

import org.springframework.core.NestedRuntimeException;

public class UserExistsException extends NestedRuntimeException {
    public UserExistsException(String msg) {
        super(msg);
    }
}
