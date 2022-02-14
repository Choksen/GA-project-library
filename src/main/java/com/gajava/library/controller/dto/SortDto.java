package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
@RequiredArgsConstructor
public class SortDto {
    private String property;
    private Direction direction;
}
