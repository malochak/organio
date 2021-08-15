package dev.malkon.events.error.exception;

import org.springframework.core.NestedRuntimeException;

public class RecordCreationException extends NestedRuntimeException {

    public RecordCreationException(String msg) {
        super(msg);
    }
}
