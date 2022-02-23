package com.gajava.library.manager;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.service.BookService;
import com.gajava.library.service.ReaderService;
import com.gajava.library.service.RecordService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class RecordManagerImplTest {

    @Mock
    private RecordService recordService;
    @Mock
    private ReaderService readerService;
    @Mock
    private BookService bookService;

    @InjectMocks
    private RecordManagerImpl recordManager;

    private final Record record = new Record();
    private final Book book = new Book();
    private final Reader reader = new Reader();
    private final Record recordParams = new Record();
    private Integer countBooksZero;
    private Integer lowRating;
    private Pageable pageable;
    private String comment;

    @Before
    public void setUp() {
        countBooksZero = 0;
        lowRating = 10;
        final Integer countBooksTen = 10;
        final Integer hundredRating = 100;
        final LocalDate dateExpectedReturnNotNull = LocalDate.now();

        book.setCountBook(countBooksTen);
        record.setBook(book);
        reader.setRating(hundredRating);
        record.setReader(reader);
        record.setDateExpectedReturn(dateExpectedReturnNotNull);

        pageable = PageRequest.of(0, 5);
        comment = "book returned";
    }

    @Test(expected = InvalidArgumentsException.class)
    public void create_recordNull_throwException() {
        recordManager.create(null);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void create_countBookZero_throwException() {
        when(bookService.findById(record.getBook().getId())).thenReturn(book);
        book.setCountBook(countBooksZero);
        recordManager.create(record);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void create_lowRating_throwException() {
        when(bookService.findById(record.getBook().getId())).thenReturn(book);
        when(readerService.findById(record.getReader().getId())).thenReturn(reader);
        reader.setRating(lowRating);
        recordManager.create(record);
    }

    @Test
    public void create_dateReceiptIsNull() {
        when(bookService.findById(record.getBook().getId())).thenReturn(book);
        when(readerService.findById(record.getReader().getId())).thenReturn(reader);
        record.setDateExpectedReturn(null);
        recordManager.create(record);
        Assert.assertNotEquals(null, record.getDateExpectedReturn());
    }

    @Test
    public void create_dateReceiptIsNotNull() {
        when(bookService.findById(record.getBook().getId())).thenReturn(book);
        when(readerService.findById(record.getReader().getId())).thenReturn(reader);
        recordManager.create(record);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void updateDateValidReturnAndComment_recordIsNull_throwException() {
        recordManager.updateDateValidReturnAndComment(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateDateValidReturnAndComment_returnNullMatches_throwException() {
        when(bookService.findById(record.getBook().getId())).thenReturn(book);
        when(readerService.findById(record.getReader().getId())).thenReturn(reader);
        when(recordService.findRecordByReaderIdAndBookId(reader.getId(), book.getId())).thenReturn(null);
        recordManager.updateDateValidReturnAndComment(record);
    }

    @Test
    public void updateDateValidReturnAndComment() {
        when(bookService.findById(record.getBook().getId())).thenReturn(book);
        when(readerService.findById(record.getReader().getId())).thenReturn(reader);
        when(recordService.findRecordByReaderIdAndBookId(reader.getId(), book.getId())).thenReturn(record);
        recordManager.updateDateValidReturnAndComment(record);
        Assert.assertNotEquals(null, record.getDateValidReturn());
    }


    @Test(expected = InvalidArgumentsException.class)
    public void findBySomething_pageableIsNull_throwException() {
        recordManager.findBySomething(recordParams, null);
    }

    @Test
    public void findBySomething_readerIsNotNull() {
        when(recordService.findAllByReader(record.getReader(), pageable)).thenReturn(Page.empty());
        recordParams.setReader(reader);
        recordManager.findBySomething(recordParams, pageable);
        verify(recordService).findAllByReader(reader, pageable);
    }

    @Test
    public void findBySomething_bookIsNotNull() {
        when(recordService.findAllByBook(record.getBook(), pageable)).thenReturn(Page.empty());
        recordParams.setBook(book);
        recordManager.findBySomething(recordParams, pageable);
        verify(recordService).findAllByBook(book, pageable);
    }

    @Test
    public void findBySomething_commentIsNotNull() {
        when(recordService.findAllByDateValidReturnIsNotNull(pageable)).thenReturn(Page.empty());
        recordParams.setComment(comment);
        recordManager.findBySomething(recordParams, pageable);
        verify(recordService).findAllByDateValidReturnIsNotNull(pageable);
    }

    @Test
    public void findBySomething_allIsNull() {
        when(recordService.findAllByDateValidReturnIsNull(pageable)).thenReturn(Page.empty());
        recordManager.findBySomething(recordParams, pageable);
        verify(recordService).findAllByDateValidReturnIsNull(pageable);
    }
}