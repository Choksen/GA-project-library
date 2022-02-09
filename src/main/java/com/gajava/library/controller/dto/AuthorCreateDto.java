package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthorCreateDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
