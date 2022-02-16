package com.gajava.library.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class RequestUpdateRecordDto {
    @NotNull
    private Long bookId;
    @NotNull
    private Long readerId;

    private String comment;
}
