package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.repository.ReaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReaderServiceImpl implements ReaderService {
    final ReaderRepository readerRepository;

    public ReaderServiceImpl(final ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public Reader save(final Reader reader) {
        log.info("Try to save reader");
        reader.setNumberReader((long) (10000 + Math.random() * 89999));
        reader.setRating(50);
        Optional<Reader> reader1 = Optional.ofNullable(readerRepository.save(reader));
        return reader1.orElseThrow(() -> new SaveEntityException("Reader"));
    }

    @Override
    public void updateBooksAndRating(final Long id, final Long idBook, final Integer rating) {
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
        log.info("Trying to add a book with id " + book.getId() + "from the reader with id " + id);
        final Reader reader = findById(id);
        final List<Book> books = reader.getBooks();
        books.add(book);
        readerRepository.save(reader);
        log.info("Added a book with id " + book.getId() + " for the reader with id " + id);
    }

    @Override
    public Reader findById(final Long id) {
        log.info("Trying to find a reader by id " + id);
        return readerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reader", id));
    }

    @Override
    public void delete(final Long id) {
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
        log.info("Looking for all the books");
        return readerRepository.findAll(pageable);
    }

}
