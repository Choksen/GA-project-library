package com.gajava.library.controller;

import com.gajava.library.controller.dto.BookDto;
import com.gajava.library.controller.dto.FindBookByDto;
import com.gajava.library.mapper.BookMapper;
import com.gajava.library.mapper.PaginationMapper;
import com.gajava.library.model.Book;
import com.gajava.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final PaginationMapper paginationMapper;

    //TODO findByYear?

    @PostMapping(value = "/save")
    public ResponseEntity<BookDto> saveBook(@RequestBody @Valid BookDto bookDto) {
        final Book book = bookMapper.fromDto(bookDto);
        bookService.create(book);
        bookDto = bookMapper.toDto(bookService.create(book));
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        final BookDto bookDto = bookMapper.toDto(bookService.findById(id));
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<BookDto>> findBySomething(@RequestBody @Valid FindBookByDto findBookByDto) {
        final Page<Book> books;
        if (findBookByDto.getGenre() != null) {
            books = bookService.findBooksByGenre(
                    findBookByDto.getGenre(),
                    paginationMapper.fromDto(findBookByDto.getPaginationDto()));
        } else if (findBookByDto.getTitle() != null) {
            books = bookService.findBooksByTitle(
                    findBookByDto.getTitle(),
                    paginationMapper.fromDto(findBookByDto.getPaginationDto()));
        } else if (findBookByDto.getCountBook() != null) {
            books = bookService.findBooksByAvailability(
                    findBookByDto.getCountBook(),
                    paginationMapper.fromDto(findBookByDto.getPaginationDto()));
        } else if (findBookByDto.getAuthors() != null) {
            books = bookService.findBookByAuthor(
                    bookMapper.authorsToAuthorsDto(findBookByDto.getAuthors()),
                    paginationMapper.fromDto(findBookByDto.getPaginationDto()));
        } else {
            books = bookService.findAll(paginationMapper.fromDto(findBookByDto.getPaginationDto()));
        }
 //       final Integer countPages = books.getTotalPages(); Надо ли?
        final List<BookDto> booksDto = bookMapper.toDto(books);
        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

}
