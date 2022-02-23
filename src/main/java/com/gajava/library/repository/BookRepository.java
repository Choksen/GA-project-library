package com.gajava.library.repository;

import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * repository working with the book
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * find book by author full name
     *
     * @param firstName first name author
     * @param lastName  last name author
     * @param pageable  pagination
     * @return page of books
     */
    @Query("SELECT b FROM Book b JOIN b.authors a " +
            "WHERE a.firstName LIKE %:first_name%  AND a.lastName LIKE %:last_name% " +
            "OR a.lastName LIKE %:first_name%  AND a.firstName LIKE %:last_name%")
    Page<Book> findAllByAuthor(@Param("first_name") String firstName,
                               @Param("last_name") String lastName,
                               Pageable pageable);

    /**
     * find books by genre
     *
     * @param genre    books genre
     * @param pageable pagination
     * @return page of books
     */
    Page<Book> findAllByGenreLike(String genre, Pageable pageable);

    /**
     * find books by title
     *
     * @param title    books title
     * @param pageable pagination
     * @return page of books
     */
    Page<Book> findBooksByTitle(String title, Pageable pageable);

    /**
     * find books by count books (>1)
     *
     * @param countBooks count books
     * @param pageable   pagination
     * @return page of books
     */
    Page<Book> findAllByCountBookGreaterThanEqual(Integer countBooks, Pageable pageable);

    /**
     * find books by count books (0)
     *
     * @param countBooks count books
     * @param pageable   pagination
     * @return page of books
     */
    Page<Book> findAllByCountBookEquals(Integer countBooks, Pageable pageable);
}
