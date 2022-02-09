package com.gajava.library.service;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book create(final Book book) {
        final Optional<Book> bookCreated = Optional.of(bookRepository.save(book));
        return bookCreated.orElseThrow();
    }

    @Override
    public void updateCountBooks(final Long id, final Integer returnedOrTaken) {
        final Book book = bookRepository.findById(id).orElseThrow();
        book.setCountBooks(book.getCountBooks() + returnedOrTaken);
        bookRepository.save(book);
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

    @Override
    public Page<Book> findBooksByGenre(final String genre) {
        final Pageable pageable = PageRequest.of(0, 5);
        final Optional<Page<Book>> books = Optional.of(bookRepository.findAllByGenre(genre, pageable));
        return books.orElseThrow();
    }

    @Override
    public Book findBookByTitle(final String title) {
        final Optional<Book> books = Optional.of(bookRepository.findBookByTitle(title));
        return books.orElseThrow();
    }

    @Override
    public Page<Book> findBooksByAvailability(final Integer count) {
        final Pageable pageable = PageRequest.of(0, 5);
        final Optional<Page<Book>> books;
        if (count == 0) {
            books = Optional.of(bookRepository.findAllByCountBooksEquals(count, pageable));
        } else {
            books = Optional.of(bookRepository.findAllByCountBooksGreaterThanEqual(count, pageable));
        }
        return books.orElseThrow();
    }

    @Override
    public Page<Book> findBookByAuthor(final Set<Author> authorSet) {
        final Pageable pageable = PageRequest.of(0, 5);
        final Optional<Page<Book>> books = Optional.of(bookRepository.findAllByAuthors(authorSet, pageable));
        return books.orElseThrow();
    }
}
