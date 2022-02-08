package com.gajava.library.service;

import com.gajava.library.model.Author;
import com.gajava.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    final AuthorRepository authorRepository;

    public AuthorServiceImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void save(final Author author) {
        final Optional<Author> author1 = Optional.of(authorRepository.save(author));
    }

    @Override
    public void delete(final Long id) {
        if (id != null) {
            authorRepository.deleteById(id);
        }
    }
}
