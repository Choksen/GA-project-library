package com.gajava.library.mapper;

import com.gajava.library.controller.dto.pagination.PaginationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper(componentModel = "spring")
public interface PaginationMapper {
    @Mappings({
            @Mapping(target = "pageNumber", expression = "java(pageable.getPageNumber())"),
            @Mapping(target = "size", expression = "java(pageable.getPageSize())"),
    })
    PaginationDto toDto(Pageable pageable);

    default Pageable fromDto(PaginationDto paginationDto) {
        final Sort sort;
        if (paginationDto.getSort() == null) {
            sort = Sort.by("id");
        } else {
            sort = Sort.by(paginationDto.getSort().getDirection(), paginationDto.getSort().getProperty());
        }
        return PageRequest.of(
                paginationDto.getPageNumber(),
                paginationDto.getSize(),
                sort);
    }
}
