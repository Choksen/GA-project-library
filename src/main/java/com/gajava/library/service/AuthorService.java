package com.gajava.library.service;

import com.gajava.library.model.Author;

public interface AuthorService {
    void save(Author author);

    void delete(Long id);
}
