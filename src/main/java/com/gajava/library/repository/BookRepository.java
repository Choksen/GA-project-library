package com.gajava.library.repository;

import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    //TODO create working method
    //   Page<Book> findAllByAuthors(Set<Author> authors, Pageable pageable);

    Page<Book> findAllByGenre(String genre, Pageable pageable);

    Page<Book> findBooksByTitle(String title, Pageable pageable);

    Page<Book> findAllByCountBooksGreaterThanEqual(Integer countBooks, Pageable pageable);

    Page<Book> findAllByCountBooksEquals(Integer countBooks, Pageable pageable);
}
