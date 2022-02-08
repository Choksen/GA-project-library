package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService{
    final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void save(final Record record) {
        if(record.getDateExpectedReturn() == null){
            record.setDateExpectedReturn(record.getDateReceipt());// +3 days
        }
        final Optional<Record> record1 = Optional.of(recordRepository.save(record));
        //added book to reader
        //added rating for reader
    }

    @Override
    public void delete(final Long id) {
        if(id != null){
            recordRepository.deleteById(id);
        }
    }

    @Override
    public Page<Record> findAllByReader(final Reader reader) {
        final Pageable pageable = PageRequest.of(0,3);
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByReader(reader,pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByBook(final Book book) {
        final Pageable pageable = PageRequest.of(0,3);
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByBook(book,pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByDateValidReturnIsNull() {
        final Pageable pageable = PageRequest.of(0,5);
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByDateValidReturnIsNotNull(pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByDateValidReturnIsNotNull() {
        final Pageable pageable = PageRequest.of(0,5);
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByDateValidReturnIsNotNull(pageable));
        return records.orElseThrow();
    }
}
