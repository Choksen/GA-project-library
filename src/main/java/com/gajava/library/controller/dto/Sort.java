package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
@RequiredArgsConstructor
public class Sort {
    private final String property;
    private final Direction direction;
}
