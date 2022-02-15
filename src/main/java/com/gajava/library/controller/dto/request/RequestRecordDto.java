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
    @NotNull
    private Long bookId;
    @NotNull
    private Long readerId;

    private LocalDate dateExpectedReturn;
}
