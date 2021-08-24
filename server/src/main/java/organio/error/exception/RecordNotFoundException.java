package organio.error.exception;

import lombok.Getter;
import org.springframework.core.NestedRuntimeException;

public class RecordNotFoundException extends NestedRuntimeException {

    @Getter
    private final String entityId;

    public RecordNotFoundException(String msg, String entityId) {
        super(msg);
        this.entityId = entityId;
    }
}
