package com.gajava.library.controller;

import com.gajava.library.controller.dto.request.RequestRecordDto;
import com.gajava.library.controller.dto.response.ResponseRecordDto;
import com.gajava.library.mapper.RecordMapper;
import com.gajava.library.model.Record;
import com.gajava.library.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "records")
public class RecordController {
    private final RecordService recordService;
    private final RecordMapper recordMapper;
//    private final PaginationMapper paginationMapper;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseRecordDto> save(@RequestBody @Valid RequestRecordDto requestRecordDto) {
        final Record record = recordMapper.fromDto(requestRecordDto);
        recordService.create(record);
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


}
