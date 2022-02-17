package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class BookDto {

    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Genre cannot be null")
    @NotBlank(message = "Genre cannot be blank")
    private String genre;

    @NotNull(message = "Year cannot be null")
    private Integer year;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Number Instances cannot be null")
    private Integer numberInstances;

    @NotNull(message = "Authors cannot be null")
    @Valid
    private Set<AuthorDto> authors;

}
