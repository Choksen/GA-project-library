package com.gajava.library.service;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface BookService {
    void save(Book book);

    void delete(Long id);

    Page<Book> findBooksByGenre(String genre);

    Book findBookByTitle(String genre);

    Page<Book> findBooksByCountBooks(Integer count);

    Page<Book> findBookByAuthor(Set<Author> authorSet);

}
