package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(final Book book) {
        log.info("Try to create book");
        final Optional<Book> bookCreated = Optional.of(bookRepository.save(book));
        return bookCreated.orElseThrow(()-> new SaveEntityException("Book"));
    }

    @Override
    public void updateCountBooks(final Long id, final Integer returnedOrTaken) {
        log.info("Try to change the number of books by 1 for the book id = " + id);
        final Book book = findById(id);
        book.setCountBook(book.getCountBook() + returnedOrTaken);
        save(book);
        log.info("Took a book with id = " + book.getId() + " and changed the number of books by 1");
    }


    @Override
    public Book findById(final Long id) {
        log.info("Trying to find an book by id " + id);
        return bookRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Book",id));
    }

    @Override
    public void delete(final Long id) {
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
        log.info("Looking for all the books");
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findBooksByGenre(final String genre, final Pageable pageable) {
        log.info("Looking for a book by genre - " + genre);
        final Optional<Page<Book>> books = Optional.ofNullable(bookRepository.findAllByGenreLike(genre, pageable));
        return books.orElseThrow(()-> new EntityNotFoundException("Book",genre));
    }

    @Override
    public Page<Book> findBooksByTitle(final String title, final Pageable pageable) {
        log.info("Looking for a book by title - " + title);
        final Optional<Page<Book>> books = Optional.ofNullable(bookRepository.findBooksByTitle(title, pageable));
        return books.orElseThrow(()-> new EntityNotFoundException("Book",title));
    }

    @Override
    public Page<Book> findBooksByAvailability(final Integer count, final Pageable pageable) {
        log.info("Looking for a book by count books - " + count);
        final Optional<Page<Book>> books;
        if (count == 0) {
            books = Optional.ofNullable(bookRepository.findAllByCountBookEquals(count, pageable));
        } else {
            books = Optional.ofNullable(bookRepository.findAllByCountBookGreaterThanEqual(count, pageable));
        }
        return books.orElseThrow(()-> new EntityNotFoundException("Book",count.toString()));
    }

    @Override
    public Page<Book> findBookByAuthor(final Author author, final Pageable pageable) {
        log.info("Looking for a book by author - " + author.getFirstName() + " " + author.getLastName());
        final Optional<Page<Book>> books = Optional.ofNullable(bookRepository.findAllByAuthor(
                author.getFirstName(),
                author.getLastName(),
                pageable));
        return books.orElseThrow(()-> new EntityNotFoundException(
                "Book",
                author.getFirstName() + " " + author.getLastName()));
    }
}
