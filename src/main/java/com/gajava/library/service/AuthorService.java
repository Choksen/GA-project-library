package com.gajava.library.service;

import com.gajava.library.model.Author;

public interface AuthorService extends CrudService<Author> {
    Author findOrCreate(Author author);
}
