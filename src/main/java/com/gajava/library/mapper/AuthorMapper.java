package com.gajava.library.mapper;

import com.gajava.library.controller.dto.AuthorDto;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper from authors
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "fullName", expression = "java(author.getFirstName() + ' ' + author.getLastName())")
    AuthorDto toDto(Author author);

    default Author fromDto(AuthorDto dto){
        final Author author = new Author();
        try {
            author.setFirstName(dto.getFullName().split(" ")[0]);
            author.setLastName(dto.getFullName().split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new InvalidArgumentsException("invalid format for writing the full name of the author");
        }
        author.setId(dto.getId());
        return author;
    }

}
