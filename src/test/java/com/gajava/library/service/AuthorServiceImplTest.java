package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Author;
import com.gajava.library.repository.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private final Author author = new Author();
    private Long id;
    private Pageable pageable;

    @Before
    public void setUp() {
        id = 80L;
        final String firstName = "maks";
        final String lastName = "cool";
        author.setFirstName(firstName);
        author.setLastName(lastName);
        pageable = PageRequest.of(0, 5);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void save_AuthorNull_throwException() {
        authorService.save(null);
    }

    @Test(expected = SaveEntityException.class)
    public void save_returnNullAfterSave_throwException() {
        when(authorRepository.save(author)).thenReturn(null);
        authorService.save(author);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void save_returnExistsAfterSave_throwException() {
        when(authorRepository.save(author)).thenThrow(DataIntegrityViolationException.class);
        authorService.save(author);
    }

    @Test
    public void save() {
        when(authorRepository.save(author)).thenReturn(author);
        authorService.save(author);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findById_IdNull_throwException() {
        authorService.findById(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_IdAbsent_throwException() {
        when(authorRepository.findById(id)).thenReturn(Optional.empty());
        authorService.findById(id);
    }

    @Test
    public void findById() {
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        authorService.findById(id);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void delete_IdNull_throwException() {
        authorService.delete(null);
    }

    @Test
    public void delete() {
        authorService.delete(id);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findAll_PageableNull_throwException() {
        authorService.findAll(null);
    }

    @Test
    public void findAll() {
        authorService.findAll(pageable);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void findOrCreate_AuthorNull_throwException() {
        authorService.findOrCreate(null);
    }

    @Test
    public void findOrCreate_findAuthor() {
        when(authorRepository.findAuthorByFirstNameAndLastName(
                author.getFirstName(),
                author.getLastName())).thenReturn(author);
        authorService.findOrCreate(author);
    }

    @Test
    public void findOrCreate_createAuthor() {
        when(authorRepository.findAuthorByFirstNameAndLastName(
                author.getFirstName(),
                author.getLastName())).thenReturn(null);
        when(authorRepository.save(author)).thenReturn(author);
        authorService.findOrCreate(author);
        verify(authorRepository).save(author);
    }
}