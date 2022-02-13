package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class BookDto {

    @NotNull
    private String title;
    @NotNull
    private String genre;
    @NotNull
    private Integer year;
    @NotNull
    private String description;
    @NotNull
    private Integer numberInstances;
    @NotNull
    @Valid
    private Set<AuthorDto> authors;

}
