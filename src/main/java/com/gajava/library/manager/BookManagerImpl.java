package com.gajava.library.manager;

import com.gajava.library.model.Book;
import com.gajava.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookManagerImpl implements BookManager {
    private final BookService bookService;

    @Override
    public Page<Book> findBooksBySomething(final Book bookParams,
                                           final Pageable pageable) {
        final Page<Book> books;
        if (bookParams.getTitle() != null) {
            books = bookService.findBooksByTitle(bookParams.getTitle(), pageable);
        } else if (bookParams.getGenre() != null) {
            books = bookService.findBooksByGenre(bookParams.getGenre(), pageable);
        } else if (bookParams.getCountBook() != null) {
            books = bookService.findBooksByAvailability(bookParams.getCountBook(), pageable);
        } else if (bookParams.getAuthors() != null) {
            books = bookService.findBookByAuthor(bookParams.getAuthors().stream().findFirst().get(), pageable);
        } else {
            books = bookService.findAll(pageable);
        }
        return books;
    }
}
