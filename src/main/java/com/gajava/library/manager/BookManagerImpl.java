package com.gajava.library.manager;

import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.service.AuthorService;
import com.gajava.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookManagerImpl implements BookManager {
    private final BookService bookService;
    private final AuthorService authorService;

    @Transactional
    @Override
    public Book create(final Book book) {
        if (Objects.isNull(book)) {
            throw new InvalidArgumentsException("The book cannot be null");
        }
        final Set<Author> authors = new HashSet<>();
        for (final Author author : book.getAuthors()) {
            authors.add(authorService.findOrCreate(author));
        }
        book.setAuthors(authors);
        return bookService.save(book);
    }

    @Override
    public Page<Book> findBooksBySomething(final Book bookParams,
                                           final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The pageable cannot be null");
        }
        final Page<Book> books;
        if (Objects.nonNull(bookParams.getTitle())) {
            books = bookService.findBooksByTitle(bookParams.getTitle(), pageable);
        } else if (Objects.nonNull(bookParams.getGenre())) {
            books = bookService.findBooksByGenre(bookParams.getGenre(), pageable);
        } else if (Objects.nonNull(bookParams.getCountBook())) {
            books = bookService.findBooksByAvailability(bookParams.getCountBook(), pageable);
        } else if (Objects.nonNull(bookParams.getAuthors())) {
            books = bookService.findBookByAuthor(bookParams.getAuthors().stream().findFirst().get(), pageable);
        } else {
            books = bookService.findAll(pageable);
        }
        return books;
    }
}
