package com.gajava.library.controller.dto.request;

import com.gajava.library.controller.dto.pagination.PaginationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class FindRecordsDto {
    private Long bookId;

    private Long readerId;

    private String comment;

    @NotNull
    private PaginationDto pagination;
}
