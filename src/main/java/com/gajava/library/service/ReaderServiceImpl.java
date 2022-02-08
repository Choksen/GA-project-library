package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import com.gajava.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService{
    final ReaderRepository readerRepository;

    public ReaderServiceImpl(final ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public void save(final Reader reader) {
        final Optional<Reader> reader1 = Optional.of(readerRepository.save(reader));
    }

    @Override
    public void delete(final Long id) {
        if(id != null){
            readerRepository.deleteById(id);
        }
    }

    @Override
    public Page<Reader> getAll() {
        final Pageable pageable = PageRequest.of(0,5);
        final Optional<Page<Reader>> readers = Optional.of(readerRepository.findAll(pageable));
        return readers.orElseThrow();
    }

    @Override
    public Page<Reader> getAllSortedByRating() {
        final Pageable pageable = PageRequest.of(0,5, Sort.by("rating").descending());
        final Optional<Page<Reader>> readers = Optional.of(readerRepository.findAll(pageable));
        return  readers.orElseThrow();
    }


}
