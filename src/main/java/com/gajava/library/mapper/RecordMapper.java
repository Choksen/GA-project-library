package com.gajava.library.mapper;

import com.gajava.library.controller.dto.request.RequestRecordDto;
import com.gajava.library.controller.dto.response.ResponseRecordDto;
import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RecordMapper {

    default Record fromDto(RequestRecordDto recordDto) {
        final Book book = new Book();
        book.setId(recordDto.getBookId());
        final Reader reader = new Reader();
        reader.setId(recordDto.getReaderId());
        final Record record = new Record();
        record.setBook(book);
        record.setReader(reader);
        return record;
    }

    @Mappings({
            @Mapping(target = "bookId", expression = "java(record.getBook().getId())"),
            @Mapping(target = "readerId", expression = "java(record.getReader().getId())")
    })
    ResponseRecordDto toDto(Record record);

}
