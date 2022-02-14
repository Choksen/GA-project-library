package com.gajava.library.service;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface BookService extends CrudService<Book> {

    void updateCountBooks(Long id, Integer returnedOrTaken);

    Page<Book> findBooksByGenre(String genre, Pageable pageable);

    Page<Book> findBooksByTitle(String title,Pageable pageable);

    Page<Book> findBooksByAvailability(Integer count,Pageable pageable);

    Page<Book> findBookByAuthor(Set<Author> authorSet,Pageable pageable);

}
