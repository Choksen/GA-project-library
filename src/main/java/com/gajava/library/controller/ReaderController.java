package com.gajava.library.controller;

import com.gajava.library.controller.dto.request.FindReadersDto;
import com.gajava.library.controller.dto.request.RequestReaderDto;
import com.gajava.library.controller.dto.response.ResponseReaderDto;
import com.gajava.library.mapper.PaginationMapper;
import com.gajava.library.mapper.ReaderMapper;
import com.gajava.library.model.Reader;
import com.gajava.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "readers")
public class ReaderController {
    private final ReaderService readerService;
    private final ReaderMapper readerMapper;
    private final PaginationMapper paginationMapper;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseReaderDto> save(@RequestBody @Valid final RequestReaderDto requestReaderDto) {
        final Reader reader = readerMapper.fromDto(requestReaderDto);
        readerService.save(reader);
        final ResponseReaderDto readerDto = readerMapper.toDto(reader);
        return new ResponseEntity<>(readerDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseReaderDto> findById(@PathVariable final Long id) {
        final ResponseReaderDto readerDto = readerMapper.toDto(readerService.findById(id));
        return new ResponseEntity<>(readerDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        readerService.delete(id);
        return new ResponseEntity<>("The reader has been deleted",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseReaderDto>> findAll(@RequestBody @Valid final FindReadersDto readersDto) {
        final Page<Reader> readers = readerService.findAll(paginationMapper.fromDto(readersDto.getPagination()));
        final List<ResponseReaderDto> responseReadersDto = readerMapper.toDto(readers);
        return new ResponseEntity<>(responseReadersDto, HttpStatus.OK);
    }
}
