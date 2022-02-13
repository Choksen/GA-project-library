package com.gajava.library.controller;

import com.gajava.library.controller.dto.BookDto;
import com.gajava.library.mapper.BookMapper;
import com.gajava.library.model.Book;
import com.gajava.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping(value = "/save")
    public ResponseEntity<BookDto> saveBook(@RequestBody @Valid BookDto bookDto) {
        final Book book = bookMapper.fromDto(bookDto);
        bookService.create(book);
        bookDto = bookMapper.toDto(bookService.create(book));
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }
}
