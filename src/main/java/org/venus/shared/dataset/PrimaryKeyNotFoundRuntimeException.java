package org.venus.shared.dataset;

public class PrimaryKeyNotFoundRuntimeException extends RuntimeException {
    public PrimaryKeyNotFoundRuntimeException(String message) {
        super(message);
    }
}
