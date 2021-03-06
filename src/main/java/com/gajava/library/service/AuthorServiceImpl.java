package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Author;
import com.gajava.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author save(final Author author) {
        if (Objects.isNull(author)) {
            throw new InvalidArgumentsException("The author cannot be null");
        }
        log.info("Try to create author " + author.getFirstName() + " " + author.getLastName());
        Author authorCreated;
        try {
            authorCreated = Optional.of(
                    authorRepository.save(author)).orElseThrow(() -> new SaveEntityException("Author"));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentsException("Such an author already exists");
        }
        log.info("Author" + author.getFirstName() + " " + author.getLastName() + " was create");
        return authorCreated;
    }

    @Override
    public Author update(final Author author) {
        if (Objects.isNull(author)) {
            throw new InvalidArgumentsException("The author cannot be null");
        }
        log.info("Try to update author " + author.getFirstName() + " " + author.getLastName());
        if(Objects.isNull(author.getId())){
            throw new InvalidArgumentsException("It is not possible to update the author with a null id");
        } else if(!authorRepository.existsById(author.getId())){
            throw new InvalidArgumentsException("It is not possible to update the author without an existing id");
        }
        Author authorUpdated;
        try {
            authorUpdated = Optional.of(
                    authorRepository.save(author)).orElseThrow(() -> new SaveEntityException("Author"));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentsException("Such an author already exists");
        }
        log.info("Author" + author.getFirstName() + " " + author.getLastName() + " was update");
        return authorUpdated;
    }

    @Override
    public Author findById(final Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidArgumentsException("The author id cannot be null");
        }
        log.info("Trying to find an author by id " + id);
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author", id));
    }

    @Override
    public void delete(final Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidArgumentsException("The author id cannot be null");
        }
        log.info("Trying to remove author with id " + id);
        try {
            authorRepository.deleteById(id);
            log.info("Deleting the author from id " + id + " successfully");
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentsException("Books are linked to this author");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Author", id);
        }
    }

    @Override
    public Page<Author> findAll(final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new InvalidArgumentsException("The pageable cannot be null");
        }
        log.info("Try to find all authors");
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author findOrCreate(final Author author) {
        if (Objects.isNull(author)) {
            throw new InvalidArgumentsException("The author cannot be null");
        }
        log.info("Find or create author " + author.getLastName() + " " + author.getLastName());
        Author authorWithId = authorRepository.findAuthorByFirstNameAndLastName(
                author.getFirstName(),
                author.getLastName());
        if (authorWithId == null) {
            authorWithId = save(author);
        }
        return authorWithId;
    }


}
