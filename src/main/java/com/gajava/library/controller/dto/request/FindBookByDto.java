package com.gajava.library.controller.dto.request;

import com.gajava.library.controller.dto.AuthorDto;
import com.gajava.library.controller.dto.pagination.PaginationDto;
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
    private AuthorDto author;
    @NotNull
    @Valid
    private PaginationDto pagination;
}
