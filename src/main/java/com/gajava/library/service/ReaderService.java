package com.gajava.library.service;

import com.gajava.library.model.Reader;
import org.springframework.data.domain.Page;

public interface ReaderService extends CrudService<Reader>{

    void updateBooksAndRating(Long id, Long idBook, Integer rating);

    void updateBooksForAdd(Long id, Long idBook);

    Page<Reader> getAll();

    Page<Reader> getAllSortedByRating();

}
