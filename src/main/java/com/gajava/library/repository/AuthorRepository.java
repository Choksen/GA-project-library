package com.gajava.library.repository;

import com.gajava.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);

}
