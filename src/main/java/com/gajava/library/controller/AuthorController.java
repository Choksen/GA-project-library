package com.gajava.library.controller;

import com.gajava.library.controller.dto.AuthorDto;
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
/*    private final AuthorMapper authorMapper;*/

    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody @Valid AuthorDto dto){
/*        Author author = authorMapper.fromDto(dto);
        AuthorCreateDto dto1 = authorMapper.toDto(author);*/
//        authorService.create(author);
        return new ResponseEntity<>(/*author*/ null,HttpStatus.CREATED);
    }
}
