package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService extends CrudService<Record> {

    Page<Record> findAllByReader(Reader reader, Pageable pageable);

    Page<Record> findAllByBook(Book book, Pageable pageable);

    Page<Record> findAllByDateValidReturnIsNull(Pageable pageable);

    Page<Record> findAllByDateValidReturnIsNotNull(Pageable pageable);

    Record findRecordByReaderIdAndBookId(Long readerId, Long bookId);
}
