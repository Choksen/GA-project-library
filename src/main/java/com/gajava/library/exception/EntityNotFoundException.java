package com.gajava.library.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(final String entity) {
        super("It is impossible to find the " + entity);
    }

    public EntityNotFoundException(final String entity, final Long id) {
        super("It is impossible to find the " + entity + " by id " + id);
    }

    public EntityNotFoundException(final String entity, final String filter) {
        super("It is impossible to find the " + entity + " from filter : " + filter);
    }
}
