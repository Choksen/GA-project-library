package com.gajava.library.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class RequestRecordDto {
    @NotNull(message = "Book id cannot be null")
    private Long bookId;

    @NotNull(message = "Reader id cannot be null")
    private Long readerId;

    private LocalDate dateExpectedReturn;
}
