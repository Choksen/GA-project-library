package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;

    @Override
    public Reader save(final Reader reader) {
        if (Objects.isNull(reader)) {
            throw new InvalidArgumentsException("The reader cannot be null");
        }
        log.info("Try to save reader");
        reader.setNumberReader((long) (10000 + Math.random() * 89999));
        reader.setRating(50);
        Optional<Reader> readerCreated;
        try {
            readerCreated = Optional.of(readerRepository.save(reader));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentsException("This email is already busy");
        }
        return readerCreated.orElseThrow(() -> new SaveEntityException("Reader"));
    }

    @Override
    public Reader update(final Reader reader) {
        if (Objects.isNull(reader)) {
            throw new InvalidArgumentsException("The reader cannot be null");
        }
        log.info("Try to update reader by id " + reader.getId());
        if (Objects.isNull(reader.getId())) {
            throw new InvalidArgumentsException("It is not possible to update the reader with a null id");
        } else if (!readerRepository.existsById(reader.getId())) {
            throw new InvalidArgumentsException("It is not possible to update the reader without an existing id");
        }
        final Reader readerBeforeUpdate = findById(reader.getId());
        reader.setBooks(readerBeforeUpdate.getBooks());
        reader.setNumberReader(readerBeforeUpdate.getNumberReader());
        final Optional<Reader> readerUpdated = Optional.of(readerRepository.save(reader));
        return readerUpdated.orElseThrow(() -> new SaveEntityException("Reader"));
    }

    @Override
    public void updateBooksAndRating(final Long id, final Long idBook, final Integer rating) {
        if (Objects.isNull(id) || Objects.isNull(idBook) || Objects.isNull(rating)) {
            throw new InvalidArgumentsException("The reader id, book id or rating cannot be null");
        }
        log.info("Starting to remove the book with id " + idBook
                + " from the reader with id " + id + " and update his rating");
        final Reader reader = findById(id);
        reader.setRating(reader.getRating() + rating);
        final List<Book> books = reader.getBooks();
        final Book bookForDelete = books.stream().filter(
                book -> book.getId().equals(idBook)).findFirst().orElseThrow(
                () -> new EntityNotFoundException("Book from the reader", idBook));
        books.remove(bookForDelete);
        readerRepository.save(reader);
        log.info("Update books and rating from reader with id " + id + " was success");
    }

    @Override
    public void updateBooksForAdd(final Long id, final Book book) {
        if (Objects.isNull(id) || Objects.isNull(book)) {
            throw new InvalidArgumentsException("The reader id or book cannot be null");
        }
        log.info("Trying to add a book with id " + book.getId() + "from the reader with id " + id);
        final Reader reader = findById(id);
        final List<Book> books = reader.getBooks();
        books.add(book);
        readerRepository.save(reader);
        log.info("Added a book with id " + book.getId() + " for the reader with id " + id);
    }

    @Override
    public Reader findById(final Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidArgumentsException("The reader id cannot be null");
        }
        log.info("Trying to find a reader by id " + id);
        return readerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reader", id));
    }

    @Override
    public void delete(final Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidArgumentsException("The reader id cannot be null");
        }
        log.info("Trying to remove reader with id " + id);
        try {
            readerRepository.deleteById(id);
            log.info("Deleting the book from id " + id + " successfully");
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentsException("Records are linked to this reader");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Reader", id);
        }
    }

    @Override
    public Page<Reader> findAll(final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The pageable cannot be null");
        }
        log.info("Looking for all the books");
        return readerRepository.findAll(pageable);
    }

}
