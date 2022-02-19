package com.gajava.library.manager;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.service.BookService;
import com.gajava.library.service.ReaderService;
import com.gajava.library.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordManagerImpl implements RecordManager {
    private final RecordService recordService;
    private final ReaderService readerService;
    private final BookService bookService;

    @Transactional
    @Override
    public Record create(final Record record) {
        final Book book = bookService.findById(record.getBook().getId());
        if (book.getCountBook() == 0) {
            throw new InvalidArgumentsException("There are currently 0 copies of this book in the library");
        }
        bookService.updateCountBooks(book.getId(), -1);

        final Reader reader = readerService.findById(record.getReader().getId());
        if (reader.getRating() < 20) {
            throw new InvalidArgumentsException("The rating of this user does not allow him to take a new book");
        }
        readerService.updateBooksForAdd(reader.getId(), book);

        record.setDateReceipt(LocalDate.now());
        if (record.getDateExpectedReturn() == null) {
            record.setDateExpectedReturn(record.getDateReceipt().plusDays(3));
        }

        return recordService.save(record);
    }

    @Transactional
    @Override
    public Record updateDateValidReturnAndComment(final Record recordParams) {
        final Book book = bookService.findById(recordParams.getBook().getId());
        bookService.updateCountBooks(book.getId(), 1);

        final Reader reader = readerService.findById(recordParams.getReader().getId());

        final Record record = Optional.of(
                        recordService.findRecordByReaderIdAndBookId(reader.getId(), book.getId()))
                .orElseThrow(() -> new EntityNotFoundException(
                        "There are no records with the id of the reader" + reader.getId()
                                + " and the id of the book " + book.getId()));
        record.setDateValidReturn(LocalDate.now());
        record.setComment(recordParams.getComment());

        final long days = ChronoUnit.DAYS.between(
                record.getDateExpectedReturn(),
                record.getDateValidReturn());
        final Integer rating = Math.toIntExact(5 + (days * -5));

        readerService.updateBooksAndRating(reader.getId(), book.getId(), rating);

        return recordService.save(record);
    }

    @Override
    public Page<Record> findBySomething(final Record record, final Pageable pageable) {
        final Page<Record> records;
        if (record.getReader() != null) {
            records = recordService.findAllByReader(record.getReader(), pageable);
        } else if (record.getBook() != null) {
            records = recordService.findAllByBook(record.getBook(), pageable);
        } else if (record.getComment() != null) {
            records = recordService.findAllByDateValidReturnIsNotNull(pageable);
        } else {
            records = recordService.findAllByDateValidReturnIsNull(pageable);
        }
        return records;
    }
}
