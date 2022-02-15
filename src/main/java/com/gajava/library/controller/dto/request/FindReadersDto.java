package com.gajava.library.controller.dto.request;

import com.gajava.library.controller.dto.pagination.PaginationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class FindReadersDto {
    @NotNull
    @Valid
    private PaginationDto pagination;
}
