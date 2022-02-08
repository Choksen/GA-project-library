package com.gajava.library.service;

import com.gajava.library.model.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReaderService {
    void save(Reader reader);

    void delete(Long id);

    Page<Reader> getAll();

    Page<Reader> getAllSortedByRating();

}
