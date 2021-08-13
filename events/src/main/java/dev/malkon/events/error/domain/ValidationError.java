package dev.malkon.events.error.domain;

public record ValidationError(String object, String field, Object rejectedValue,
                              String message) implements RequestSubError {
}
