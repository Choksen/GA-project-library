package com.gajava.library.mapper;

import com.gajava.library.controller.dto.request.RequestReaderDto;
import com.gajava.library.controller.dto.response.ResponseReaderDto;
import com.gajava.library.model.Reader;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReaderMapper {

    ResponseReaderDto toDto(Reader reader);

    Reader fromDto(RequestReaderDto readerDto);

    List<ResponseReaderDto> toDto(Page<Reader> readers);
}
