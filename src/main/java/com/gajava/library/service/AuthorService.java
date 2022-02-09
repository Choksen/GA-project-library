package com.gajava.library.service;

import com.gajava.library.model.Author;

public interface AuthorService {
    //name,last name
    Author create(Author author);

    Author findById(Long id);

    void delete(Long id);
}
