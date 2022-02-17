package com.gajava.library.mapper;

import com.gajava.library.controller.dto.AuthorDto;
import com.gajava.library.controller.dto.BookDto;
import com.gajava.library.controller.dto.request.FindBookByDto;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "numberInstances", source = "book.countBook")
    BookDto toDto(Book book);

    @Mapping(target = "countBook", source = "dto.numberInstances")
    Book fromDto(BookDto dto);

    List<BookDto> toDto(Page<Book> books);

    @Mapping(target = "authors",expression = "java(authorToAuthorDto(findBookByDto.getAuthor()))")
    Book fromDto(FindBookByDto findBookByDto);

    default Set<Author> authorsToAuthorsDto(Set<AuthorDto> authorsDto) {
        final Set<Author> authors = new HashSet<>();
        for (final AuthorDto authorDto : authorsDto) {
            final Author author = new Author();
            author.setFirstName(authorDto.getFullName().split(" ")[0]);
            author.setLastName(authorDto.getFullName().split(" ")[1]);
            authors.add(author);
        }
        return authors;
    }


    default Set<AuthorDto> authorsDtoToAuthors(Set<Author> authors) {
        final Set<AuthorDto> authorsDto = new HashSet<>();
        for (final Author author : authors) {
            final AuthorDto authorDto = new AuthorDto();
            authorDto.setFullName(author.getFirstName() + ' ' + author.getLastName());
            authorsDto.add(authorDto);
        }
        return authorsDto;
    }

    default Set<Author> authorToAuthorDto(AuthorDto authorDto) {
        if (authorDto == null) {
            return null;
        }
        final Author author = new Author();
        author.setFirstName(authorDto.getFullName().split(" ")[0]);
        author.setLastName(authorDto.getFullName().split(" ")[1]);
        final Set<Author> authors = new HashSet<>();
        authors.add(author);
        return authors;
    }
}
