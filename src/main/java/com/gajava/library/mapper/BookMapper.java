package com.gajava.library.mapper;

import com.gajava.library.controller.dto.AuthorDto;
import com.gajava.library.controller.dto.BookDto;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapping<BookDto,Book> {
    @Mapping(target = "numberInstances", source = "book.countBooks")
    BookDto toDto(Book book);

    @Mapping(target = "countBooks",source = "dto.numberInstances")
    Book fromDto(BookDto dto);

    default Set<Author> authorsToAuthorsDto(Set<AuthorDto> authorsDto){
        final Set<Author> authors = new HashSet<>();
        for(final AuthorDto authorDto : authorsDto){
        final Author author = new Author();
        author.setFirstName(authorDto.getFullName().split(" ")[0]);
        author.setLastName(authorDto.getFullName().split(" ")[1]);
        authors.add(author);
        }
        return authors;
    }
    default Set<AuthorDto> authorsDtoToAuthors(Set<Author> authors){
        final Set<AuthorDto> authorsDto = new HashSet<>();
        for(final Author author : authors){
            final AuthorDto authorDto = new AuthorDto();
            authorDto.setFullName(author.getFirstName() + ' ' + author.getLastName());
            authorsDto.add(authorDto);
        }
        return  authorsDto;
    }


}
