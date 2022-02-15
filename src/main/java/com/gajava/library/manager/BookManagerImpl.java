package com.gajava.library.manager;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookManagerImpl implements BookManager {
    private final BookService bookService;

    @Override
    public Page<Book> findBooksBySomething(final String title,
                                           final String genre,
                                           final Integer countBook,
                                           final Set<Author> authors,
                                           final Pageable pageable) {
        final Page<Book> books;
        if (title != null) {
            books = bookService.findBooksByTitle(title, pageable);
        } else if (genre != null) {
            books = bookService.findBooksByGenre(genre, pageable);
        } else if (countBook != null) {
            books = bookService.findBooksByAvailability(countBook, pageable);
        } else if (authors != null) {
            books = bookService.findBookByAuthor(authors,pageable);
        } else {
            books = bookService.findAll(pageable);
        }
        return books;
    }
}
