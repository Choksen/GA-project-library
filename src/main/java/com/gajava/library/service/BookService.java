package com.gajava.library.service;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A service that works with books
 */
public interface BookService extends CrudService<Book> {

    /**
     * update count books by books id
     *
     * @param id              book id
     * @param returnedOrTaken a number that adds or removes an instance of a book
     */
    void updateCountBooks(Long id, Integer returnedOrTaken);

    /**
     * find books by genre
     *
     * @param genre    books genre
     * @param pageable pagination
     * @return page of books
     */
    Page<Book> findBooksByGenre(String genre, Pageable pageable);

    /**
     * find books by title
     *
     * @param title    books title
     * @param pageable pagination
     * @return page of books
     */
    Page<Book> findBooksByTitle(String title, Pageable pageable);

    /**
     * find books by genre
     *
     * @param count    count books
     * @param pageable pagination
     * @return page of books
     */
    Page<Book> findBooksByAvailability(Integer count, Pageable pageable);

    /**
     * find books by genre
     *
     * @param author   author of the book
     * @param pageable pagination
     * @return page of books
     */
    Page<Book> findBookByAuthor(Author author, Pageable pageable);

}
