package com.gajava.library.controller.dto.pagination;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class PaginationDto {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer size;

    private SortDto sort;

}
