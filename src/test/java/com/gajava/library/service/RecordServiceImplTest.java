package com.gajava.library.service;


import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.repository.RecordRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class RecordServiceImplTest {

    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private RecordServiceImpl recordService;

    private Long id;
    private final Record record = new Record();
    private final Book book = new Book();
    private final Reader reader = new Reader();
    private Pageable pageable;
    private Long readerId;
    private Long bookId;


    @Before
    public void setUp() {
        id = 10L;
        pageable = PageRequest.of(0, 5);
        readerId = 22L;
        bookId = 33L;
    }

    @Test
    public void save() {
        when(recordRepository.save(record)).thenReturn(record);
        recordService.save(record);
    }

    @Test
    public void findById() {
        when(recordRepository.findById(id)).thenReturn(Optional.of(record));
        recordService.findById(id);
    }

    @Test
    public void delete() {
        recordService.delete(id);
    }

    @Test
    public void findAll() {
        recordService.findAll(pageable);
    }

    @Test
    public void findAllByReader() {
        when(recordRepository.findAllByReader(reader, pageable)).thenReturn(Page.empty());
        recordService.findAllByReader(reader, pageable);
    }

    @Test
    public void findAllByBook() {
        when(recordRepository.findAllByBook(book, pageable)).thenReturn(Page.empty());
        recordService.findAllByBook(book, pageable);
    }

    @Test
    public void findAllByDateValidReturnIsNull() {
        when(recordRepository.findAllByDateValidReturnIsNull(pageable)).thenReturn(Page.empty());
        recordService.findAllByDateValidReturnIsNull(pageable);
    }

    @Test
    public void findAllByDateValidReturnIsNotNull() {
        when(recordRepository.findAllByDateValidReturnIsNotNull(pageable)).thenReturn(Page.empty());
        recordService.findAllByDateValidReturnIsNotNull(pageable);
    }

    @Test
    public void findRecordByReaderIdAndBookId() {
        when(recordRepository.findFirstByReaderIdAndBookIdAndDateValidReturnIsNull(readerId, bookId))
                .thenReturn(record);
        recordService.findRecordByReaderIdAndBookId(readerId, bookId);
    }
}