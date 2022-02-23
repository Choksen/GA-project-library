package com.gajava.library.manager;

import com.gajava.library.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Manager records service
 */
public interface RecordManager {
    /**
     * create record
     *
     * @param record record entity
     * @return created record
     */
    Record create(Record record);

    /**
     * update record date return and comment when reader return book
     *
     * @param record record entity
     * @return created record
     */
    Record updateDateValidReturnAndComment(Record record);

    /**
     * find records by filters
     *
     * @param record   filters
     * @param pageable pagination
     * @return page id records
     */
    Page<Record> findBySomething(Record record, Pageable pageable);
}
