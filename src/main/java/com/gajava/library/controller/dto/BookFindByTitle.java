package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookFindByTitle {
    @NotNull
    private String title;
    @NotNull
    @Valid
    private Pagination pagination;
}
