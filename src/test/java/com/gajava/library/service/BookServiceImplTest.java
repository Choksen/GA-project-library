package com.gajava.library.service;


import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import com.gajava.library.repository.BookRepository;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private final Book book = new Book();
    private Long id;
    private String title;
    private String genre;
    private Integer countBooks;
    private final Author author = new Author();
    private final Set<Author> authors = new HashSet<>();
    private Pageable pageable;
    private Integer returnedOrTaken;


    @Before
    public void setUp() {
        id = 8L;
        title = "The Witcher";
        genre = "fantasy";
        final String description = "the best";
        countBooks = 5;
        final Integer year = 1994;
        author.setFirstName("Andrzej");
        author.setLastName("Sapkowski");
        authors.add(author);

        book.setTitle(title);
        book.setCountBook(countBooks);
        book.setGenre(genre);
        book.setAuthors(authors);
        book.setDescription(description);
        book.setYear(year);

        pageable = PageRequest.of(0, 5);
        returnedOrTaken = -1;

    }

    @Test(expected = InvalidArgumentsException.class)
    public void save_BookNull_throwException() {
        bookService.save(null);
    }

    @Test(expected = SaveEntityException.class)
    public void save_returnNullAfterSave_throwException() {
        when(bookRepository.save(book)).thenReturn(null);
        bookService.save(book);
    }

    @Test
    public void save() {
        when(bookRepository.save(book)).thenReturn(book);
        bookService.save(book);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void updateCountBooks_IdOrNumberOfChangesNull() {
        bookService.updateCountBooks(id, null); //TODO others???
    }

    @Test
    public void updateCountBooks() {
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        bookService.updateCountBooks(id, returnedOrTaken);
        Assert.assertNotEquals(countBooks, book.getCountBook());
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findById_IdNull_throwException() {
        bookService.findById(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_IdAbsent_throwException() {
        when(bookRepository.findById(id)).thenReturn(Optional.empty());
        bookService.findById(id);
    }

    @Test
    public void findById() {
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        bookService.findById(id);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void delete_IdNull_throwException() {
        bookService.delete(null);
    }

    @Test
    public void delete() {
        bookService.delete(id);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findAll_PageableNull_throwException() {
        bookService.findAll(null);
    }

    @Test
    public void findAll() {
        bookService.findAll(pageable);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findBooksByGenre_GenreNull_throwException() {
        bookService.findBooksByGenre(null, pageable);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findBooksByGenre_returnNull_throwException() {
        when(bookRepository.findAllByGenreLike(genre, pageable)).thenReturn(null);
        bookService.findBooksByGenre(genre, pageable);
    }

    @Test
    public void findBooksByGenre() {
        when(bookRepository.findAllByGenreLike(genre, pageable)).thenReturn(Page.empty());
        bookService.findBooksByGenre(genre, pageable);
    }


    @Test(expected = EntityNotFoundException.class)
    public void findBooksByTitle_returnNull_throwException() {
        when(bookRepository.findBooksByTitle(title, pageable)).thenReturn(null);
        bookService.findBooksByGenre(title, pageable);
    }

    @Test
    public void findBooksByTitle() {
        when(bookRepository.findBooksByTitle(title, pageable)).thenReturn(Page.empty());
        bookService.findBooksByTitle(title, pageable);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findBooksByAvailability() {
        bookService.findBooksByAvailability(null, pageable);
    }

    @Test
    public void findBooksByAvailability_CountZero() {
        when(bookRepository.findAllByCountBookEquals(0, pageable)).thenReturn(Page.empty());
        bookService.findBooksByAvailability(0, pageable);
        verify(bookRepository).findAllByCountBookEquals(0, pageable);
    }

    @Test
    public void findBooksByAvailability_CountNotZero() {
        when(bookRepository.findAllByCountBookGreaterThanEqual(4, pageable)).thenReturn(Page.empty());
        bookService.findBooksByAvailability(4, pageable);
        verify(bookRepository).findAllByCountBookGreaterThanEqual(4, pageable);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findBookByAuthor_authorsOrPageableIsNull() {
        bookService.findBookByAuthor(author, null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findBookByAuthor_returnEmptyPage() {
        when(bookRepository.findAllByAuthor(
                author.getFirstName(),
                author.getFirstName(),
                pageable)).thenReturn(Page.empty());
        bookService.findBookByAuthor(author, pageable);
    }

}