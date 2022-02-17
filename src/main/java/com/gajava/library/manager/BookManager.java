package com.gajava.library.manager;

import com.gajava.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookManager {
    Page<Book> findBooksBySomething(Book bookParams, Pageable pageable);
}
