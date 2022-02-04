package com.gajava.library.repository;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findAuthorsByFirstNameAndBooks(String firstName, Set<Book> book);
}
