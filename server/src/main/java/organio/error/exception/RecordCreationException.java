package organio.error.exception;

import org.springframework.core.NestedRuntimeException;

public class RecordCreationException extends NestedRuntimeException {

    public RecordCreationException(String msg) {
        super(msg);
    }
}
