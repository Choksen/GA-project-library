package com.gajava.library.service;

import com.gajava.library.model.Author;

/**
 * A service that works with authors
 */
public interface AuthorService extends CrudService<Author> {
    /**
     * find the author or create if does not exist
     *
     * @param author author
     * @return found or created author
     */
    Author findOrCreate(Author author);
}
