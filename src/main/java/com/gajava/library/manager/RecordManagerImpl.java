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
        if (book.getCountBooks() == 0) {
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
    public Record updateDateValidReturnAndComment(final Long readerId, final Long bookId,
                                                  final String comment, final LocalDate dateValidReturn) {
        final Book book = bookService.findById(bookId);
        bookService.updateCountBooks(book.getId(), 1);

        final Reader reader = readerService.findById(readerId);

        //TODO exception если не найдет такую запись
        final Record record = Optional.of(recordService.findRecordByReaderIdAndBookId(readerId, bookId)).orElseThrow();
        record.setDateValidReturn(LocalDate.now());
        record.setComment(comment);

        final long days = ChronoUnit.DAYS.between(
                record.getDateExpectedReturn(),
                record.getDateValidReturn());
        final Integer rating = Math.toIntExact(5 + (days * -5));

        readerService.updateBooksAndRating(reader.getId(), book.getId(), rating);

        final Optional<Record> recordUpdate = Optional.of(recordService.create(record));
        return recordUpdate.orElseThrow();
    }

    @Override
    public Page<Record> findBySomething(final Reader reader, final Book book,
                                        final LocalDate dateValidReturn, final Pageable pageable) {
        final Page<Record> records;
        if(reader != null){
            records = recordService.findAllByReader(reader,pageable);
        } else if(book != null){
            records = recordService.findAllByBook(book,pageable);
        } else if(dateValidReturn != null){ //TODO поменять логику обработки на руках или нет, тк работает криво
            records = recordService.findAllByDateValidReturnIsNotNull(pageable);
        } else {
            records = recordService.findAllByDateValidReturnIsNull(pageable);
        }
        return records;
    }
}
