package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that works with records
 */
public interface RecordService extends CrudService<Record> {

    /**
     * find record by reader
     *
     * @param reader   reader
     * @param pageable pagination
     * @return page of records
     */
    Page<Record> findAllByReader(Reader reader, Pageable pageable);

    /**
     * find records by book
     *
     * @param book     book
     * @param pageable pagination
     * @return page of records
     */
    Page<Record> findAllByBook(Book book, Pageable pageable);

    /**
     * find records by books on hands
     *
     * @param pageable pagination
     * @return page of records
     */
    Page<Record> findAllByDateValidReturnIsNull(Pageable pageable);

    /**
     * find records by returned books
     *
     * @param pageable pagination
     * @return page of records
     */
    Page<Record> findAllByDateValidReturnIsNotNull(Pageable pageable);

    /**
     * find records by reader id and book id
     *
     * @param readerId reader id
     * @param bookId   book id
     * @return page of records
     */
    Record findRecordByReaderIdAndBookId(Long readerId, Long bookId);
}
