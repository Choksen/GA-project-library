package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(final Book book) {
        if (Objects.isNull(book)) {
            throw new InvalidArgumentsException("The book cannot be null");
        }
        log.info("Try to create book");
        final Optional<Book> bookCreated = Optional.of(bookRepository.save(book));
        return bookCreated.orElseThrow(() -> new SaveEntityException("Book"));
    }

    @Override
    public Book update(final Book book) {
        if (Objects.isNull(book)) {
            throw new InvalidArgumentsException("The book cannot be null");
        }
        log.info("Try to update book by id " + book.getId());
        if (Objects.isNull(book.getId())) {
            throw new InvalidArgumentsException("It is not possible to update the book with a null id");
        } else if (!bookRepository.existsById(book.getId())) {
            throw new InvalidArgumentsException("It is not possible to update the book without an existing id");
        }
        final Book bookBeforeUpdate = findById(book.getId());
        book.setAuthors(bookBeforeUpdate.getAuthors());
        final Optional<Book> bookUpdated = Optional.of(bookRepository.save(book));
        return bookUpdated.orElseThrow(() -> new SaveEntityException("Book"));
    }

    @Override
    public void updateCountBooks(final Long id, final Integer returnedOrTaken) {
        if (Objects.isNull(id) || Objects.isNull(returnedOrTaken)) {
            throw new InvalidArgumentsException("The book id or number of changes cannot be null");
        }
        log.info("Try to change the number of books by 1 for the book id = " + id);
        final Book book = findById(id);
        book.setCountBook(book.getCountBook() + returnedOrTaken);
        save(book);
        log.info("Took a book with id = " + book.getId() + " and changed the number of books by 1");
    }


    @Override
    public Book findById(final Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidArgumentsException("The book id cannot be null");
        }
        log.info("Trying to find an book by id " + id);
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book", id));
    }

    @Override
    public void delete(final Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidArgumentsException("The book id cannot be null");
        }
        log.info("Trying to remove book with id " + id);
        try {
            bookRepository.deleteById(id);
            log.info("Deleting the book from id " + id + " successfully");
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentsException("Records are linked to this book");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Book", id);
        }
    }

    @Override
    public Page<Book> findAll(final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The pageable cannot be null");
        }
        log.info("Looking for all the books");
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findBooksByGenre(final String genre, final Pageable pageable) {
        if (Objects.isNull(genre) || Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The book genre or pageable cannot be null");
        }
        log.info("Looking for a book by genre - " + genre);
        final Optional<Page<Book>> books = Optional.ofNullable(bookRepository.findAllByGenreLike(genre, pageable));
        return books.orElseThrow(() -> new EntityNotFoundException("Book", genre));
    }

    @Override
    public Page<Book> findBooksByTitle(final String title, final Pageable pageable) {
        if (Objects.isNull(title) || Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The book title or pageable cannot be null");
        }
        log.info("Looking for a book by title - " + title);
        final Optional<Page<Book>> books = Optional.ofNullable(bookRepository.findBooksByTitle(title, pageable));
        return books.orElseThrow(() -> new EntityNotFoundException("Book", title));
    }

    @Override
    public Page<Book> findBooksByAvailability(final Integer count, final Pageable pageable) {
        if (Objects.isNull(count) || Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The book count or pageable cannot be null");
        }
        log.info("Looking for a book by count books - " + count);
        final Optional<Page<Book>> books;
        if (count == 0) {
            books = Optional.ofNullable(bookRepository.findAllByCountBookEquals(count, pageable));
        } else {
            books = Optional.ofNullable(bookRepository.findAllByCountBookGreaterThanEqual(count, pageable));
        }
        return books.orElseThrow(() -> new EntityNotFoundException("Book", count.toString()));
    }

    @Override
    public Page<Book> findBookByAuthor(final Author author, final Pageable pageable) {
        if (Objects.isNull(author) || Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The book author or pageable cannot be null");
        }
        log.info("Looking for a book by author - " + author.getFirstName() + " " + author.getLastName());
        final Optional<Page<Book>> books = Optional.ofNullable(bookRepository.findAllByAuthor(
                author.getFirstName(),
                author.getLastName(),
                pageable));
        return books.orElseThrow(() -> new EntityNotFoundException(
                "Book",
                author.getFirstName() + " " + author.getLastName()));
    }
}
