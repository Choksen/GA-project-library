package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class Pagination {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer size;
    private List<Sort> sort;

}
