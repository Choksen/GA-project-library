package com.gajava.library.controller;

import com.gajava.library.controller.dto.AuthorDto;
import com.gajava.library.mapper.AuthorMapper;
import com.gajava.library.model.Author;
import com.gajava.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping(value = "/save")
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody @Valid AuthorDto authorDto){
        final Author author = authorMapper.fromDto(authorDto);
        authorDto = authorMapper.toDto(authorService.create(author));
        return new ResponseEntity<>(authorDto,HttpStatus.CREATED);
    }
}
