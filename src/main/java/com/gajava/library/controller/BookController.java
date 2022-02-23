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

/**
 * the controller working with the books
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final PaginationMapper paginationMapper;
    private final BookManager bookManager;


    @PostMapping(value = "/save")
    public ResponseEntity<BookDto> saveBook(@RequestBody @Valid final BookDto bookDto) {
        final Book book = bookMapper.fromDto(bookDto);
        final BookDto responseBookDto = bookMapper.toDto(bookManager.create(book));
        return new ResponseEntity<>(responseBookDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable final Long id) {
        final BookDto bookDto = bookMapper.toDto(bookService.findById(id));
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        bookService.delete(id);
        return new ResponseEntity<>("The book has been deleted", HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<BookDto>> findBySomething(@RequestBody @Valid final FindBookByDto findBookByDto) {
        final Book bookParams = bookMapper.fromDto(findBookByDto);
        final Page<Book> books = bookManager.findBooksBySomething(
                bookParams,
                paginationMapper.fromDto(findBookByDto.getPagination()));
        final List<BookDto> booksDto = bookMapper.toDto(books);
        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

}
