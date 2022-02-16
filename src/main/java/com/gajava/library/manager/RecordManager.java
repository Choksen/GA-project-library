package com.gajava.library.manager;

import com.gajava.library.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RecordManager {
    Record create(Record record);

    Record updateDateValidReturnAndComment(Record record);

    Page<Record> findBySomething(Record record, Pageable pageable);
}
