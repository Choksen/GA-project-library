package com.gajava.library.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseRecordDto {
    @NotNull(message = "Book id cannot be null")
    private Long bookId;

    @NotNull(message = "Reader id cannot be null")
    private Long readerId;

    @NotNull(message = "Date receipt cannot be null")
    private LocalDate dateReceipt;

    @NotNull(message = "Date expected return cannot be null")
    private LocalDate dateExpectedReturn;

    private LocalDate dateValidReturn;
    private String comment;


}
