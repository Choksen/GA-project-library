package com.gajava.library.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class RequestUpdateRecordDto {
    @NotNull(message = "Book id cannot be null")
    private Long bookId;

    @NotNull(message = "Reader id cannot be null")
    private Long readerId;

    private String comment;
}
