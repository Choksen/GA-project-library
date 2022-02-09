package com.gajava.library.controller;

import com.gajava.library.model.Author;
import com.gajava.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Author> saveAuthor(@RequestBody @Valid Author author){
        if(author == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        authorService.save(author);
        return new ResponseEntity<>(author,HttpStatus.CREATED);
    }
}
