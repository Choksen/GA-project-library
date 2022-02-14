package com.gajava.library.mapper;

import com.gajava.library.controller.dto.PaginationDto;
import com.gajava.library.controller.dto.SortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper(componentModel = "spring")
public interface PaginationMapper {
    @Mappings({
            @Mapping(target = "pageNumber",expression = "java(pageable.getPageNumber())"),
            @Mapping(target = "size",expression = "java(pageable.getPageSize())"),
            })
    PaginationDto toDto(Pageable pageable);

    default Sort sortToSortDto(SortDto sortDto) {
        return Sort.by(sortDto.getDirection(),sortDto.getProperty());
    }

    default Pageable fromDto(PaginationDto paginationDto){
        final Sort sort = Sort.by(paginationDto.getSortDto().getDirection(), paginationDto.getSortDto().getProperty());
        return PageRequest.of(
                paginationDto.getPageNumber(),
                paginationDto.getSize(),
                sort);
    }
    /*final Pageable pageable = PageRequest.of(0, 5, Sort.by("rating").descending());*/
}
