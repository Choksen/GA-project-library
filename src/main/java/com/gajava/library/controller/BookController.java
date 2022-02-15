package com.gajava.library.controller;

import com.gajava.library.controller.dto.BookDto;
import com.gajava.library.controller.dto.request.FindBookByDto;
import com.gajava.library.manager.BookManager;
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
    private final BookManager bookManager;


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

    //TODO findByYear?
    //TODO переделать в сервисы Manager
    @GetMapping(value = "")
    public ResponseEntity<List<BookDto>> findBySomething(@RequestBody @Valid FindBookByDto findBookByDto) {
        final Page<Book> books = bookManager.findBooksBySomething(
                findBookByDto.getTitle(),
                findBookByDto.getGenre(),
                findBookByDto.getCountBook(),
                bookMapper.authorsToAuthorsDto(findBookByDto.getAuthors()),
                paginationMapper.fromDto(findBookByDto.getPagination()));
        //final Integer countPages = books.getTotalPages(); Надо ли передавать на фронт количество страниц?
        final List<BookDto> booksDto = bookMapper.toDto(books);
        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

}
