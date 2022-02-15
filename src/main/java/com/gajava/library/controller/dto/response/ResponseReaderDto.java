package com.gajava.library.controller.dto.response;

import com.gajava.library.controller.dto.BookDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseReaderDto {
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    @Email
    private final String email;
    @NotNull
    private final String phone;
    @NotNull
    private final Long numberReader;
    @NotNull
    private final Integer rating;
    private final List<BookDto> bookDtoList;
}
