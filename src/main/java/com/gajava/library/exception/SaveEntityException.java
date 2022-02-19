package com.gajava.library.exception;

public class SaveEntityException extends RuntimeException {
    public SaveEntityException(final String entity) {
        super("It is impossible to save the " + entity + " entity");
    }
}
