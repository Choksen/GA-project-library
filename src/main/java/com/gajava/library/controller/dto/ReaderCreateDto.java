package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class ReaderCreateDto {

    @NotNull
    private final String name;
    @NotNull
    private final String lastname;
    @NotNull
    @Email
    private final String email;
    @NotNull
    private final String phone;

}
