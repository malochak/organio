package dev.malkon.events.error.exception;

import org.springframework.core.NestedRuntimeException;

public class RecordNotFoundException extends NestedRuntimeException {

    private final String entityId;

    public RecordNotFoundException(String msg, String entityId) {
        super(msg);
        this.entityId = entityId;
    }
}
