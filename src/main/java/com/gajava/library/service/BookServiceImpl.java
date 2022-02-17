package com.gajava.library.service;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(final BookRepository bookRepository, final AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    //TODO понять как тут реализовать транзакцию
    //TODO manager service
    @Transactional
    @Override
    public Book create(final Book book) {
        final Set<Author> authors = new HashSet<>();
        for (final Author author : book.getAuthors()) {
            authors.add(authorService.findOrCreate(author));
        }
        book.setAuthors(authors);
        final Optional<Book> bookCreated = Optional.of(bookRepository.save(book));
        return bookCreated.orElseThrow();
    }

    @Override
    public void updateCountBooks(final Long id, final Integer returnedOrTaken) {
        final Book book = bookRepository.findById(id).orElseThrow();
        book.setCountBook(book.getCountBook() + returnedOrTaken);
        bookRepository.save(book);
        log.info("Took a book with id = " + book.getId() + " and reduced the number of books by 1");
    }


    @Override
    public Book findById(final Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(final Long id) {
        if (id != null) {
            bookRepository.deleteById(id);
        }
    }

    //TODO exception
    @Override
    public Page<Book> findAll(final Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findBooksByGenre(final String genre, final Pageable pageable) {
        final Optional<Page<Book>> books = Optional.of(bookRepository.findAllByGenre(genre, pageable));
        return books.orElseThrow();
    }

    @Override
    public Page<Book> findBooksByTitle(final String title, final Pageable pageable) {
        final Optional<Page<Book>> books = Optional.of(bookRepository.findBooksByTitle(title, pageable));
        return books.orElseThrow();
    }

    @Override
    public Page<Book> findBooksByAvailability(final Integer count, final Pageable pageable) {
        final Optional<Page<Book>> books;
        if (count == 0) {
            books = Optional.of(bookRepository.findAllByCountBookEquals(count, pageable));
        } else {
            books = Optional.of(bookRepository.findAllByCountBookGreaterThanEqual(count, pageable));
        }
        return books.orElseThrow();
    }

    @Override
    public Page<Book> findBookByAuthor(final Author author, final Pageable pageable) {
        final Optional<Page<Book>> books = Optional.of(bookRepository.findAllByAuthor(
                author.getFirstName(),
                author.getLastName(),
                pageable));
        return books.orElseThrow();
    }
}
