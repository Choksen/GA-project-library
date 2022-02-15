package com.gajava.library.service;

import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.repository.ReaderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {
    final ReaderRepository readerRepository;

    public ReaderServiceImpl(final ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public Reader create(final Reader reader) {
        reader.setNumberReader((long) (10000 + Math.random() * 89999));
        reader.setRating(50);
        Optional<Reader> reader1 = Optional.of(readerRepository.save(reader));
        return reader1.orElseThrow();
    }

    @Override
    public void updateBooksAndRating(final Long id, final Long idBook, final Integer rating) {
        final Reader reader = readerRepository.findById(id).orElseThrow();
        reader.setRating(reader.getRating() + rating);
        final List<Book> books = reader.getBooks();
        final Book bookForDelete = books.stream().filter(book -> book.getId().equals(idBook)).findFirst().orElseThrow();
        books.remove(bookForDelete);
        readerRepository.save(reader);
    }

    @Override
    public void updateBooksForAdd(final Long id, final Book book) {
        final Reader reader = readerRepository.findById(id).orElseThrow();
        final List<Book> books = reader.getBooks();
        books.add(book);
        readerRepository.save(reader);
    }

    @Override
    public Reader findById(final Long id) {
        return readerRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(final Long id) {
        if (id != null) {
            readerRepository.deleteById(id);
        }
    }

    //TODO add exception
    @Override
    public Page<Reader> findAll(final Pageable pageable) {
        return readerRepository.findAll(pageable);
    }

}
