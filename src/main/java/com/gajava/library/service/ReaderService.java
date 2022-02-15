package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;

public interface ReaderService extends CrudService<Reader> {

    void updateBooksAndRating(Long id, Long idBook, Integer rating);

    void updateBooksForAdd(Long id, Book book);

}
