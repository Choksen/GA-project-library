package com.gajava.library.service;

import com.gajava.library.model.Author;
import com.gajava.library.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    final AuthorRepository authorRepository;

    public AuthorServiceImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(final Author author) {
        final Optional<Author> authorCreated = Optional.of(authorRepository.save(author));
        return authorCreated.orElseThrow();
    }

    @Override
    public Author findById(final Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    //TODO exception if null
    @Override
    public void delete(final Long id) {
        if (id != null) {
            authorRepository.deleteById(id);
        }
    }

    //TODO added exception
    @Override
    public Page<Author> findAll(final Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author findOrCreate(final Author author) {
        Author authorWithId = authorRepository.findAuthorByFirstNameAndLastName(
                author.getFirstName(),
                author.getLastName());
        if (authorWithId == null) {
            authorWithId = authorRepository.save(author);
        }
        return authorWithId;
    }


}
