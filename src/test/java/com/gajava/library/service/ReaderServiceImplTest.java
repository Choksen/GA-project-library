package com.gajava.library.service;


import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.repository.ReaderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Test {@link ReaderServiceImpl}
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ReaderServiceImplTest {

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private ReaderServiceImpl readerService;

    private final Reader reader = new Reader();
    private final Book book1 = new Book();
    private final Book book2 = new Book();
    private final List<Book> books = new ArrayList<>();
    private Long id;
    private Long idBook1;
    private Integer changeRating;
    private Integer startRating;
    private final List<Book> booksDefault = new ArrayList<>();

    @Before
    public void setUp() {
        final String email = "12335@mail.ru";
        final String phone = "80538165232";


        reader.setEmail(email);
        reader.setPhone(phone);
        idBook1 = 10L;
        book1.setId(idBook1);
        books.add(book1);
        final Long idBook2 = 100L;
        book2.setId(idBook2);
        books.add(book2);
        reader.setBooks(books);

        id = 7L;
        startRating = 50;
        changeRating = 80;
        booksDefault.addAll(books);
    }

    @Test
    public void save() {
        when(readerRepository.save(reader)).thenReturn(reader);
        readerService.save(reader);
        Assert.assertNotEquals(null, reader.getRating());
        Assert.assertNotEquals(null, reader.getNumberReader());
    }

    @Test
    public void updateBooksAndRating() {
        when(readerRepository.findById(id)).thenReturn(Optional.of(reader));
        reader.setRating(startRating);
        when(readerRepository.save(reader)).thenReturn(reader);
        readerService.updateBooksAndRating(id, idBook1, changeRating);
        Assert.assertEquals(changeRating + startRating, (long) reader.getRating());
        Assert.assertNotEquals(booksDefault, reader.getBooks());
    }

    @Test
    public void updateBooksForAdd() {
        when(readerRepository.findById(id)).thenReturn(Optional.of(reader));
        when(readerRepository.save(reader)).thenReturn(reader);
        readerService.updateBooksForAdd(id, book1);
        Assert.assertNotEquals(booksDefault, reader.getBooks());
    }

}