package com.gajava.library.controller.dto.pagination;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

/**
 * sort dto
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SortDto {
    private String property;
    private Direction direction;
}
