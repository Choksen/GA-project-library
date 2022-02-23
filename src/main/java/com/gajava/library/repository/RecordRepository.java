package com.gajava.library.repository;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository working with the record
 */
public interface RecordRepository extends JpaRepository<Record, Long> {

    /**
     * find record by reader
     *
     * @param reader   reader entity
     * @param pageable pagination
     * @return page of records
     */
    Page<Record> findAllByReader(Reader reader, Pageable pageable);

    /**
     * find record by book
     *
     * @param book     book entity
     * @param pageable pagination
     * @return page of records
     */
    Page<Record> findAllByBook(Book book, Pageable pageable);

    /**
     * find record by book returned
     *
     * @param pageable pagination
     * @return page of records
     */
    Page<Record> findAllByDateValidReturnIsNull(Pageable pageable);

    /**
     * find record by books on hands
     *
     * @param pageable pagination
     * @return page of records
     */
    Page<Record> findAllByDateValidReturnIsNotNull(Pageable pageable);

    /**
     * find record By Reader id And Book id And DateValidReturn Is Null
     *
     * @param readerId reader if
     * @param bookId   book id
     * @return page of records
     */
    Record findFirstByReaderIdAndBookIdAndDateValidReturnIsNull(Long readerId, Long bookId);

}
