package com.gajava.library.mapper;

import com.gajava.library.controller.dto.AuthorDto;
import com.gajava.library.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    //TODO ArrayIndexOutOfBoundsException handler
    @Mappings({
            @Mapping(target = "firstName", expression = "java(dto.getFullName().split(\" \")[0])"),
            @Mapping(target = "lastName", expression = "java(dto.getFullName().split(\" \")[1])")
    })
    Author fromDto(AuthorDto dto);

    @Mapping(target = "fullName", expression = "java(author.getFirstName() + ' ' + author.getLastName())")
    AuthorDto toDto(Author author);
}
