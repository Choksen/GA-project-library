package com.gajava.library.mapper;

import com.gajava.library.controller.dto.request.RequestReaderDto;
import com.gajava.library.controller.dto.request.RequestUpdateReaderDto;
import com.gajava.library.controller.dto.response.ResponseReaderDto;
import com.gajava.library.model.Reader;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Mapper from readers
 */
@Mapper(componentModel = "spring")
public interface ReaderMapper {

    ResponseReaderDto toDto(Reader reader);

    Reader fromDto(RequestReaderDto readerDto);

    Reader fromDto(RequestUpdateReaderDto readerDto);

    List<ResponseReaderDto> toDto(Page<Reader> readers);
}
