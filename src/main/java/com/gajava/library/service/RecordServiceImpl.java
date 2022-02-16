package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    @Override
    public Record create(final Record record) {
        final Optional<Record> recordCreated = Optional.of(recordRepository.save(record));
        return recordCreated.orElseThrow();
    }

    @Override
    public Record findById(final Long id) {
        return recordRepository.findById(id).orElseThrow();
    }


    @Override
    public void delete(final Long id) {
        if (id != null) {
            recordRepository.deleteById(id);
        }
    }

    //TODO added exception
    @Override
    public Page<Record> findAll(final Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    @Override
    public Page<Record> findAllByReader(final Reader reader,final Pageable pageable) {
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByReader(reader, pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByBook(final Book book,final Pageable pageable) {
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByBook(book, pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByDateValidReturnIsNull(final Pageable pageable) {
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByDateValidReturnIsNull(pageable));
        return records.orElseThrow();
    }

    @Override
    public Page<Record> findAllByDateValidReturnIsNotNull(final Pageable pageable) {
        final Optional<Page<Record>> records = Optional.of(recordRepository.findAllByDateValidReturnIsNotNull(pageable));
        return records.orElseThrow();
    }

    @Override
    public Record findRecordByReaderIdAndBookId(final Long readerId,final Long bookId) {
        final Optional<Record> record = Optional.of(
                recordRepository.findFirstByReaderIdAndBookIdAndDateValidReturnIsNull(readerId,bookId));
        return record.orElseThrow();
    }
}
