package com.gajava.library.mapper;

import com.gajava.library.controller.dto.BookDto;
import com.gajava.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "numberInstances",source = "book.countBooks")
    BookDto toDto(Book book);
    @Mapping(target = "countBooks",source = "dto.numberInstances")
    Book fromDto(BookDto dto);
}
