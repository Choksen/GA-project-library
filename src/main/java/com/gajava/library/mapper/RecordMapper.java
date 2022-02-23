package com.gajava.library.mapper;

import com.gajava.library.controller.dto.request.FindRecordsDto;
import com.gajava.library.controller.dto.request.RequestDefaultUpdateRecordDto;
import com.gajava.library.controller.dto.request.RequestRecordDto;
import com.gajava.library.controller.dto.request.RequestUpdateRecordDto;
import com.gajava.library.controller.dto.response.ResponseRecordDto;
import com.gajava.library.model.Book;
import com.gajava.library.model.Reader;
import com.gajava.library.model.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Mapper from records
 */
@Mapper(componentModel = "spring")
public interface RecordMapper {

    List<ResponseRecordDto> toDto(Page<Record> records);

    @Mappings({
            @Mapping(target = "bookId", expression = "java(record.getBook().getId())"),
            @Mapping(target = "readerId", expression = "java(record.getReader().getId())")
    })
    ResponseRecordDto toDto(Record record);

    Record fromDto(RequestDefaultUpdateRecordDto requestRecordDto);

    default Record fromDto(final RequestRecordDto recordDto) {
        final Book book = new Book();
        book.setId(recordDto.getBookId());
        final Reader reader = new Reader();
        reader.setId(recordDto.getReaderId());
        final Record record = new Record();
        record.setBook(book);
        record.setReader(reader);
        record.setDateExpectedReturn(recordDto.getDateExpectedReturn());
        return record;
    }


    default Record fromDto(final FindRecordsDto recordDto) {
        if (recordDto.getBookId() != null) {
            return new Record(new Book(recordDto.getBookId()));
        } else if (recordDto.getReaderId() != null) {
            return new Record(new Reader(recordDto.getReaderId()));
        } else {
            return new Record(recordDto.getComment());
        }
    }

    default Record fromDto(final RequestUpdateRecordDto updateRecordDto) {
        final Book book = new Book(updateRecordDto.getBookId());
        final Reader reader = new Reader(updateRecordDto.getReaderId());
        return new Record(
                book,
                reader,
                updateRecordDto.getComment());
    }

}
