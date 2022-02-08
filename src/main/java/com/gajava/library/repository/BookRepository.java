package com.gajava.library.repository;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByAuthors(Set<Author> authors, Pageable pageable);

    Page<Book> findAllByGenre(String genre, Pageable pageable);

    Book findBookByTitle(String title);

    Page<Book> findAllByCountBooksAfter(Integer countBook, Pageable pageable);

}
