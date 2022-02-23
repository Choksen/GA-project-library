package com.gajava.library.manager;

import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Manager book service
 */
public interface BookManager {

    /**
     * create book
     *
     * @param book book
     * @return created book
     */
    Book create(Book book);

    /**
     * find book by filters
     *
     * @param bookParams filters
     * @param pageable   pagination
     * @return page of books
     */
    Page<Book> findBooksBySomething(Book bookParams, Pageable pageable);
}
