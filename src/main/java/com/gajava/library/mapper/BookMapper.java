package com.gajava.library.mapper;

import com.gajava.library.controller.dto.AuthorDto;
import com.gajava.library.controller.dto.BookDto;
import com.gajava.library.controller.dto.request.FindBookByDto;
import com.gajava.library.controller.dto.request.RequestUpdateBookDto;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.model.Author;
import com.gajava.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Mapper from books
 */
@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "numberInstances", source = "book.countBook")
    BookDto toDto(Book book);

    List<BookDto> toDto(Page<Book> books);

    @Mapping(target = "countBook", source = "dto.numberInstances")
    Book fromDto(BookDto dto);

    @Mapping(target = "authors", expression = "java(authorToAuthorDto(findBookByDto.getAuthor()))")
    Book fromDto(FindBookByDto findBookByDto);

    @Mapping(target = "countBook", source = "bookDto.numberInstances")
    Book fromDto(RequestUpdateBookDto bookDto);

    default Set<Author> authorsToAuthorsDto(final Set<AuthorDto> authorsDto) {
        final Set<Author> authors = new HashSet<>();
        for (final AuthorDto authorDto : authorsDto) {
            final Author author = new Author();
            try {
                author.setFirstName(authorDto.getFullName().split(" ")[0]);
                author.setLastName(authorDto.getFullName().split(" ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidArgumentsException("invalid format for writing the full name of the author");
            }
            authors.add(author);
        }
        return authors;
    }


    default Set<AuthorDto> authorsDtoToAuthors(final Set<Author> authors) {
        final Set<AuthorDto> authorsDto = new HashSet<>();
        for (final Author author : authors) {
            final AuthorDto authorDto = new AuthorDto();
            authorDto.setFullName(author.getFirstName() + ' ' + author.getLastName());
            authorsDto.add(authorDto);
        }
        return authorsDto;
    }

    default Set<Author> authorToAuthorDto(final AuthorDto authorDto) {
        if (authorDto == null) {
            return null;
        }
        final Author author = new Author();
        try {
            author.setFirstName(authorDto.getFullName().split(" ")[0]);
            author.setLastName(authorDto.getFullName().split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentsException("invalid format for writing the full name of the author");
        }
        final Set<Author> authors = new HashSet<>();
        authors.add(author);
        return authors;
    }

}
