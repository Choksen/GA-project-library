package com.gajava.library.repository;

import com.gajava.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository working with the authors
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
    /**
     * find author by first name and last name
     *
     * @param firstName first name author
     * @param lastName  last name author
     * @return author entity
     */
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
