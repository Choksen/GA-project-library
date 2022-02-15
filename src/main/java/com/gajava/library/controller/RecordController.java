package com.gajava.library.controller;

import com.gajava.library.controller.dto.request.RequestRecordDto;
import com.gajava.library.controller.dto.response.ResponseRecordDto;
import com.gajava.library.mapper.RecordMapper;
import com.gajava.library.model.Record;
import com.gajava.library.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "records")
public class RecordController {
    private final RecordService recordService;
    private final RecordMapper recordMapper;
//    private final PaginationMapper paginationMapper;

    //TODO может быть заменить дефолт в рекордМаппинг и в дто поменять приходит бук дто и рекод дто сокрощенные, которые хранят в себе только айди?
    // Доделать обратный маппинг, потому что поля бук и ридер не заполняются, но сохраняются
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseRecordDto> save(@RequestBody @Valid RequestRecordDto requestRecordDto){
        final Record record = recordMapper.fromDto(requestRecordDto);
        recordService.create(record);
        return new ResponseEntity<>(recordMapper.toDto(record), HttpStatus.CREATED);
    }
}
