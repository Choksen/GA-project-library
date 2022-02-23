package com.gajava.library.controller.dto.request;

import com.gajava.library.controller.dto.pagination.PaginationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * find reader dto
 */
@Getter
@Setter
@RequiredArgsConstructor
public class FindReadersDto {
    @NotNull(message = "Pagination cannot be null")
    @Valid
    private PaginationDto pagination;
}
