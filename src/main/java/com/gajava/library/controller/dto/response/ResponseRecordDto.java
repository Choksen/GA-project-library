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
    @NotNull
    private Long bookId;
    @NotNull
    private Long readerId;
    @NotNull
    private LocalDate dateReceipt;
    @NotNull
    private LocalDate dateExpectedReturn;

    private LocalDate dateValidReturn;
    private String comment;



}
