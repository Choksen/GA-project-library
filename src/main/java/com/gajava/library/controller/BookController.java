package com.gajava.library.controller;

import com.gajava.library.model.Book;
import com.gajava.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Book> saveBook(@RequestBody @Valid Book book){
        if(book == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.save(book);
        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

}
