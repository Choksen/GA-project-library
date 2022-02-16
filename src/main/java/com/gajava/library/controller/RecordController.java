package com.gajava.library.controller;

import com.gajava.library.controller.dto.BookDto;
import com.gajava.library.controller.dto.request.FindBookByDto;
import com.gajava.library.controller.dto.request.RequestRecordDto;
import com.gajava.library.controller.dto.response.ResponseRecordDto;
import com.gajava.library.manager.RecordManager;
import com.gajava.library.mapper.PaginationMapper;
import com.gajava.library.mapper.RecordMapper;
import com.gajava.library.model.Book;
import com.gajava.library.model.Record;
import com.gajava.library.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "records")
public class RecordController {
    private final RecordService recordService;
    private final RecordManager recordManager;
    private final RecordMapper recordMapper;
    private final PaginationMapper paginationMapper;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseRecordDto> save(@RequestBody @Valid RequestRecordDto requestRecordDto) {
        final Record record = recordMapper.fromDto(requestRecordDto);
        recordManager.create(record);
        return new ResponseEntity<>(recordMapper.toDto(record), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseRecordDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(recordMapper.toDto(recordService.findById(id)),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void delete(@PathVariable Long id){
        recordService.delete(id);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ResponseRecordDto>> findBySomething(@RequestBody @Valid RequestRecordDto recordDto) {
        final Page<Record> records = recordManager.findBySomething( //TODO DTO with pagination
                recordDto.getReaderId(),
                recordDto.getBookId(),
                recordDto.getDateExpectedReturn(),
                paginationMapper.fromDto(recordDto.getPagination()));
        //final Integer countPages = books.getTotalPages(); Надо ли передавать на фронт количество страниц?
        final List<ResponseRecordDto> responseRecordDto = recordMapper.toDto(records);
        return new ResponseEntity<>(responseRecordDto, HttpStatus.OK);
    }


}
