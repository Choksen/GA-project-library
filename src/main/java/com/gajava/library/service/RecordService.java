package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface RecordService extends CrudService<Record> {

    Record updateDateValidReturnAndComment(Long readerId, Long bookId, String comment, LocalDate dateValidReturn);

    Page<Record> findAllByReader(Reader reader);

    Page<Record> findAllByBook(Book book);

    Page<Record> findAllByDateValidReturnIsNull();

    Page<Record> findAllByDateValidReturnIsNotNull();

}
