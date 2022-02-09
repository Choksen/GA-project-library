package com.gajava.library.service;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface BookService {
    // title,genre,year,description,number_instances
    Book create(Book book);

    void updateCountBooks(Long id, Integer returnedOrTaken);

    Book findById(Long id);

    void delete(Long id);

    Page<Book> findBooksByGenre(String genre);

    Book findBookByTitle(String title);

    Page<Book> findBooksByAvailability(Integer count);

    Page<Book> findBookByAuthor(Set<Author> authorSet);

}
