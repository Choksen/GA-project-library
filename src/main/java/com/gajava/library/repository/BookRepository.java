package com.gajava.library.repository;

import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.authors a " +
            "WHERE a.firstName LIKE %:first_name%  AND a.lastName LIKE %:last_name%")
    Page<Book> findAllByAuthor(@Param("first_name") String firstName,
                               @Param("last_name") String lastName,
                               Pageable pageable);

    Page<Book> findAllByGenre(String genre, Pageable pageable);

    Page<Book> findBooksByTitle(String title, Pageable pageable);

    Page<Book> findAllByCountBookGreaterThanEqual(Integer countBooks, Pageable pageable);

    Page<Book> findAllByCountBookEquals(Integer countBooks, Pageable pageable);
}
