package com.gajava.library.manager;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RecordManager {
    Record create(Record record);

    Record updateDateValidReturnAndComment(Long readerId, Long bookId, String comment, LocalDate dateValidReturn);

    Page<Record> findBySomething(Reader reader, Book book, LocalDate dateValidReturn, Pageable pageable);
}
