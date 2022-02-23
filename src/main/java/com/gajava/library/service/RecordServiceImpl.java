package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.repository.RecordRepository;
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
@RequiredArgsConstructor
@Slf4j
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    @Override
    public Record save(final Record record) {
        if (Objects.isNull(record)) {
            throw new InvalidArgumentsException("The record cannot be null");
        }
        log.info("Try to save record");
        final Optional<Record> recordCreated = Optional.of(recordRepository.save(record));
        return recordCreated.orElseThrow(() -> new SaveEntityException("Record"));
    }

    @Override
    public Record update(final Record record) {
        if (Objects.isNull(record)) {
            throw new InvalidArgumentsException("The record cannot be null");
        }
        log.info("Try to update record by id " + record.getId());
        if (Objects.isNull(record.getId())) {
            throw new InvalidArgumentsException("It is not possible to update the record with a null id");
        } else if (!recordRepository.existsById(record.getId())) {
            throw new InvalidArgumentsException("It is not possible to update the record without an existing id");
        }
        final Record recordBeforeUpdate = findById(record.getId());
        record.setReader(recordBeforeUpdate.getReader());
        record.setBook(recordBeforeUpdate.getBook());
        final Optional<Record> recordUpdated = Optional.of(recordRepository.save(record));
        return recordUpdated.orElseThrow(() -> new SaveEntityException("Reader"));
    }

    @Override
    public Record findById(final Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidArgumentsException("The record id cannot be null");
        }
        log.info("Trying to find a record by id " + id);
        return recordRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Record", id));
    }

    @Override
    public void delete(final Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidArgumentsException("The record id cannot be null");
        }
        log.info("Trying to remove record with id " + id);
        try {
            recordRepository.deleteById(id);
            log.info("Deleting the record from id " + id + " successfully");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Record", id);
        }
    }

    @Override
    public Page<Record> findAll(final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The pageable cannot be null");
        }
        log.info("Looking for all the records");
        return recordRepository.findAll(pageable);
    }

    @Override
    public Page<Record> findAllByReader(final Reader reader, final Pageable pageable) {
        if (Objects.isNull(reader) || Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The reader or pageable cannot be null");
        }
        log.info("Looking for a record by reader id = " + reader.getId());
        final Optional<Page<Record>> records = Optional.ofNullable(
                recordRepository.findAllByReader(reader, pageable));
        return records.orElseThrow(() -> new EntityNotFoundException("Record", "reader with id " + reader.getId()));
    }

    @Override
    public Page<Record> findAllByBook(final Book book, final Pageable pageable) {
        if (Objects.isNull(book) || Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The book or pageable cannot be null");
        }
        log.info("Looking for a record by book id = " + book.getId());
        final Optional<Page<Record>> records = Optional.ofNullable(
                recordRepository.findAllByBook(book, pageable));
        return records.orElseThrow(() -> new EntityNotFoundException("Record", "book with id " + book.getId()));
    }

    @Override
    public Page<Record> findAllByDateValidReturnIsNull(final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The pageable cannot be null");
        }
        log.info("Looking for a record by book on hands");
        final Optional<Page<Record>> records = Optional.ofNullable(
                recordRepository.findAllByDateValidReturnIsNull(pageable));
        return records.orElseThrow(() -> new EntityNotFoundException("Record", "book on hands"));
    }

    @Override
    public Page<Record> findAllByDateValidReturnIsNotNull(final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The pageable cannot be null");
        }
        log.info("Looking for a record by book returned");
        final Optional<Page<Record>> records = Optional.ofNullable(
                recordRepository.findAllByDateValidReturnIsNotNull(pageable));
        return records.orElseThrow(() -> new EntityNotFoundException("Record", "book returned"));
    }

    @Override
    public Record findRecordByReaderIdAndBookId(final Long readerId, final Long bookId) {
        if (Objects.isNull(readerId) || Objects.isNull(bookId)) {
            throw new InvalidArgumentsException("The book id or reader id cannot be null");
        }
        log.info("Looking for a record by reader id " + readerId + " and book id " + bookId);
        final Optional<Record> record = Optional.ofNullable(
                recordRepository.findFirstByReaderIdAndBookIdAndDateValidReturnIsNull(readerId, bookId));
        return record.orElseThrow(() -> new EntityNotFoundException(
                "Record",
                "reader id " + readerId + " and book id " + bookId));
    }
}
