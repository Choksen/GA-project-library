package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;

/**
 * A service that works with readers
 */
public interface ReaderService extends CrudService<Reader> {

    /**
     * update readers books and rating
     *
     * @param id     reader id
     * @param idBook book id
     * @param rating reader rating
     */
    void updateBooksAndRating(Long id, Long idBook, Integer rating);

    /**
     * update readers books(add)
     *
     * @param id   reader id
     * @param book book id
     */
    void updateBooksForAdd(Long id, Book book);

}
