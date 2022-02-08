package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import org.springframework.data.domain.Page;

public interface RecordService {
    void save(Record record);

    void delete(Long id);

    Page<Record> findAllByReader(Reader reader);

    Page<Record> findAllByBook(Book book);

    Page<Record> findAllByDateValidReturnIsNull();

    Page<Record> findAllByDateValidReturnIsNotNull();

}
