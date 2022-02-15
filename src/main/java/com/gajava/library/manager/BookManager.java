package com.gajava.library.manager;

import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface BookManager {
    Page<Book> findBooksBySomething(String title,
                                    String genre,
                                    Integer countBook,
                                    Set<Author> authors,
                                    Pageable pageable);

}
