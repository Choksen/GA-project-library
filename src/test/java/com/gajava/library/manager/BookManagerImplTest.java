package com.gajava.library.manager;

import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.service.AuthorService;
import com.gajava.library.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookManagerImplTest {

    @Mock
    private AuthorService authorService;
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookManagerImpl bookManager;

    private final Author author = new Author();
    private final Book book = new Book();
    private final Book bookParams = new Book();
    private Pageable pageable;


    @Before
    public void setUp() {
        final String firstName = "Max";
        final String lastName = "cool";
        author.setFirstName(firstName);
        author.setLastName(lastName);
        book.setAuthors(Set.of(author));
        pageable = PageRequest.of(0, 5);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void create_bookNull_throwException() {
        bookManager.create(null);
    }

    @Test
    public void create() {
        when(authorService.save(author)).thenReturn(author);
        when(bookService.save(book)).thenReturn(book);
        bookManager.create(book);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findBooksBySomething_pageableNull_throwException() {
        bookManager.findBooksBySomething(book, null);
    }

    @Test
    public void findBooksBySomething_titleNotNull() {
        when(bookService.findBooksByTitle(bookParams.getTitle(), pageable)).thenReturn(Page.empty());
        bookParams.setTitle("test");
        bookManager.findBooksBySomething(bookParams, pageable);
        verify(bookService).findBooksByTitle(bookParams.getTitle(), pageable);
    }

    @Test
    public void findBooksBySomething_genreNotNull() {
        when(bookService.findBooksByGenre(bookParams.getGenre(), pageable)).thenReturn(Page.empty());
        bookParams.setGenre("test");
        bookManager.findBooksBySomething(bookParams, pageable);
        verify(bookService).findBooksByGenre(bookParams.getGenre(), pageable);
    }

    @Test
    public void findBooksBySomething_countBookNotNull() {
        bookParams.setCountBook(0);
        when(bookService.findBooksByAvailability(bookParams.getCountBook(), pageable)).thenReturn(Page.empty());
        bookManager.findBooksBySomething(bookParams, pageable);
        verify(bookService).findBooksByAvailability(bookParams.getCountBook(), pageable);
    }

    @Test
    public void findBooksBySomething_authorsNotNull() {
        bookParams.setAuthors(Set.of(author));
        when(bookService.findBookByAuthor(bookParams.getAuthors().stream().findFirst().get(), pageable))
                .thenReturn(Page.empty());
        bookManager.findBooksBySomething(bookParams, pageable);
        verify(bookService).findBookByAuthor(bookParams.getAuthors().stream().findFirst().get(), pageable);
    }

    @Test
    public void findBooksBySomething_allNull() {
        when(bookService.findAll(pageable)).thenReturn(Page.empty());
        bookManager.findBooksBySomething(bookParams, pageable);
        verify(bookService).findAll(pageable);
    }
}