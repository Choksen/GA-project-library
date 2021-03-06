package com.gajava.library.repository;

import com.gajava.library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository working with the reader
 */
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}
