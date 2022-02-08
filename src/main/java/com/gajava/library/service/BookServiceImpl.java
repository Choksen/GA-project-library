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
    public void save(final Book book) {
        final Optional<Book> book1 = Optional.of(bookRepository.save(book));
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
    public Page<Book> findBooksByCountBooks(final Integer count) {
        final Pageable pageable = PageRequest.of(0, 5);
        final Optional<Page<Book>> books = Optional.of(bookRepository.findAllByCountBooksAfter(count, pageable));
        return books.orElseThrow();
    }

    @Override
    public Page<Book> findBookByAuthor(final Set<Author> authorSet) {
        final Pageable pageable = PageRequest.of(0, 5);
        final Optional<Page<Book>> books = Optional.of(bookRepository.findAllByAuthors(authorSet, pageable));
        return books.orElseThrow();
    }
}
