package com.gajava.library.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * request for default update record
 */
@Getter
@Setter
@RequiredArgsConstructor
public class RequestDefaultUpdateRecordDto {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "Date receipt cannot be null")
    private LocalDate dateReceipt;

    @NotNull(message = "Date expected return cannot be null")
    private LocalDate dateExpectedReturn;

    private LocalDate dateValidReturn;
    private String comment;
}
