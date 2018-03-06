package org.codegas.commons.domain.exception;

public class EntityConflictException extends RuntimeException {

    public EntityConflictException(String message) {
        super(message);
    }
}
