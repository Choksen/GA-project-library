package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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

}
