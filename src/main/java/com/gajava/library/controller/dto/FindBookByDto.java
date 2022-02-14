package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class FindBookByDto {
    private String title;
    private String genre;
    private Integer countBook;
    private Set<AuthorDto> authors;
    @NotNull
    @Valid
    private PaginationDto paginationDto;
}
