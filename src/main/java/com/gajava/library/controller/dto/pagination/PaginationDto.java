package com.gajava.library.controller.dto.pagination;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class PaginationDto {
    @NotNull(message = "Page number cannot be null")
    private Integer pageNumber;

    @NotNull(message = "Size cannot be null")
    private Integer size;

    private SortDto sort;

}
