package com.gajava.library.controller.dto.request;

import com.gajava.library.controller.dto.pagination.PaginationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class FindRecordsDto {
    private Long bookId;

    private Long readerId;

    private String comment;

    @NotNull(message = "Pagination cannot be null")
    private PaginationDto pagination;
}
