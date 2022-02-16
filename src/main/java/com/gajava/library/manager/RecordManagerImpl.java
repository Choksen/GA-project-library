package com.gajava.library.manager;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.service.BookService;
import com.gajava.library.service.ReaderService;
import com.gajava.library.service.RecordService;
import lombok.RequiredArgsConstructor;
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
            throw new IllegalArgumentException("no books available");
        }
        bookService.updateCountBooks(book.getId(), -1);

        final Reader reader = readerService.findById(record.getReader().getId());
        if (reader.getRating() < 20) {
            throw new IllegalArgumentException("So low rating");
        }
        readerService.updateBooksForAdd(reader.getId(),book);

        record.setDateReceipt(LocalDate.now());
        if (record.getDateExpectedReturn() == null) {
            record.setDateExpectedReturn(record.getDateReceipt().plusDays(3));
        }
        final Optional<Record> record1 = Optional.of(recordService.create(record));

        return record1.orElseThrow();
    }

    @Transactional
    @Override
    public Record updateDateValidReturnAndComment(final Record recordParams) {
        final Book book = bookService.findById(recordParams.getBook().getId());
        bookService.updateCountBooks(book.getId(), 1);

        final Reader reader = readerService.findById(recordParams.getReader().getId());

        //TODO exception если не найдет такую запись
        final Record record = Optional.of(
                recordService.findRecordByReaderIdAndBookId(reader.getId(), book.getId())).orElseThrow();
        record.setDateValidReturn(LocalDate.now());
        record.setComment(recordParams.getComment());

        final long days = ChronoUnit.DAYS.between(
                record.getDateExpectedReturn(),
                record.getDateValidReturn());
        final Integer rating = Math.toIntExact(5 + (days * -5));

        readerService.updateBooksAndRating(reader.getId(), book.getId(), rating);

        final Optional<Record> recordUpdate = Optional.of(recordService.create(record));
        return recordUpdate.orElseThrow();
    }

    @Override
    public Page<Record> findBySomething(final Record record, final Pageable pageable) {
        final Page<Record> records;
        if(record.getReader() != null){
            records = recordService.findAllByReader(record.getReader(),pageable);
        } else if(record.getBook() != null){
            records = recordService.findAllByBook(record.getBook(),pageable);
        } else if(record.getComment() != null){ //TODO поменять логику обработки на руках или нет, тк работает криво
            records = recordService.findAllByDateValidReturnIsNotNull(pageable); //возвращена
        } else {
            records = recordService.findAllByDateValidReturnIsNull(pageable); //на руках
        }
        return records;
    }
}
