package com.gajava.library.service;

import com.gajava.library.model.Reader;
import org.springframework.data.domain.Page;

public interface ReaderService {
    //name,lastname,email,phone
    Reader create(Reader reader);

    void updateBooksAndRating(Long id, Long idBook, Integer rating);

    void updateBooksForAdd(Long id, Long idBook);

    Reader findById(Long id);

    void delete(Long id);

    Page<Reader> getAll();

    Page<Reader> getAllSortedByRating();

}
