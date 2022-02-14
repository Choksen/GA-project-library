package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.repository.RecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {
    final RecordRepository recordRepository;
    final ReaderService readerService;
    final BookService bookService;

    public RecordServiceImpl(final RecordRepository recordRepository,
                             final ReaderService readerService,
                             final BookService bookService) {
        this.recordRepository = recordRepository;
        this.readerService = readerService;
        this.bookService = bookService;
    }

    //TODO manager service
    @Transactional
    @Override
    public Record create(final Record record) {
        final Book book = bookService.findById(record.getBook().getId());
        if (book.getCountBooks() == 0) {
            throw new IllegalArgumentException("no books available");
        }
        bookService.updateCountBooks(book.getId(), -1);

        final Reader reader = readerService.findById(record.getReader().getId());
        if (reader.getRating() < 20) {
            throw new IllegalArgumentException("So low rating");
        }
        readerService.updateBooksForAdd(reader.getId(), record.getBook().getId());

        record.setDateReceipt(LocalDate.now());
        if (record.getDateExpectedReturn() == null) {
            record.setDateExpectedReturn(record.getDateReceipt().plusDays(3));
        }
        final Optional<Record> record1 = Optional.of(recordRepository.save(record));

        return record1.orElseThrow();
    }

    @Override
    public Record findById(final Long id) {
        return recordRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Record updateDateValidReturnAndComment(final Long readerId, final Long bookId,
                                                  final String comment, final LocalDate dateValidReturn) {
        final Book book = bookService.findById(bookId);
        bookService.updateCountBooks(book.getId(), 1);

        final Reader reader = readerService.findById(readerId);

        final Record record = recordRepository.findRecordByReaderIdAndBookId(readerId, bookId);
        record.setDateValidReturn(LocalDate.now());
        record.setComment(comment);

        final long days = ChronoUnit.DAYS.between(
                record.getDateExpectedReturn(),
                record.getDateValidReturn());
        final Integer rating = Math.toIntExact(5 + (days * -5));

        readerService.updateBooksAndRating(reader.getId(), book.getId(), rating);

        final Optional<Record> recordUpdate = Optional.of(recordRepository.save(record));
        return recordUpdate.orElseThrow();
    }

    @Override
    public void delete(final Long id) {
        if (id != null) {
            recordRepository.deleteById(id);
        }
    }
    //TODO added exception
    @Override
    public Page<Record> findAll(final Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    @Override
    public Page<Record> findAllByReader(final Reader reader) {
        final Pageable pageable = PageRequest.of(0, 3);
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByReader(reader, pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByBook(final Book book) {
        final Pageable pageable = PageRequest.of(0, 3);
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByBook(book, pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByDateValidReturnIsNull() {
        final Pageable pageable = PageRequest.of(0, 5);
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByDateValidReturnIsNotNull(pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByDateValidReturnIsNotNull() {
        final Pageable pageable = PageRequest.of(0, 5);
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByDateValidReturnIsNotNull(pageable));
        return records.orElseThrow();
    }
}
