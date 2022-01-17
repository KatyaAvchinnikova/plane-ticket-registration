package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.request.AirportRequestDto;
import com.example.businesslayernew.dto.response.AirportResponseDto;
import com.example.businesslayernew.mapper.AirportEntityToAirportResponseDtoMapper;
import com.example.businesslayernew.mapper.AirportRequestDtoToAirportEntityMapper;
import com.example.businesslayernew.service.AirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO: здесь и далее: зачем геттеры и сеттеры для классов бизнес-логики?
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/airports")
@Api("Airport controller")
public class AirportController {

    private final AirportService airportService;
    private final AirportRequestDtoToAirportEntityMapper airportDtoToAirportEntityMapper;
    private final AirportEntityToAirportResponseDtoMapper airportEntityToAirportDtoMapper;


    //    TODO: зачем коммент? Если делаем комменты с описанием метода - пишем как доку. Здесь и далее
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new airport")
    public ResponseEntity<AirportResponseDto> create(@RequestBody AirportRequestDto request) {

        AirportResponseDto airportResponseDto =
                airportEntityToAirportDtoMapper.map(
                        airportService.create(airportDtoToAirportEntityMapper.map(request)));

//        TODO: лишние пробелы. Юзаем ctrl+alt+L
        return new ResponseEntity<>(airportResponseDto, HttpStatus.CREATED);

    }

    @GetMapping
    @ApiOperation("read all airports")
    public List<AirportResponseDto> readAll() {
        //    TODO: одна строчка - одна точка
        return airportService.readAll()
                             .stream()
                             .map((airportEntityToAirportDtoMapper::map))
                             .collect(
                                     Collectors.toList());
    }

    //    TODO: потерян слеш
    @GetMapping("/{id}")
    @ApiOperation("read airport by id")
//    TODO: Вроде как можно не указывать литерал переменной, если он совпадает с наименованием параметра
    public ResponseEntity<AirportResponseDto> readById(@PathVariable Long id) {
        return new ResponseEntity<>(airportEntityToAirportDtoMapper
                .map(airportService.readById(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation("update airport")
    public ResponseEntity<AirportResponseDto> update(@PathVariable Long id,
            @RequestBody AirportRequestDto request) {
//        TODO: что это за чудо кодочитабельности?
        AirportResponseDto airportResponse = airportEntityToAirportDtoMapper
                        .map(airportService.update(id, airportDtoToAirportEntityMapper
                        .map(request)));
        return new ResponseEntity<>(airportResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    TODO: описания методов с большой буквы
    @ApiOperation("delete airport")
    public ResponseEntity<AirportResponseDto> delete(@PathVariable Long id) {
        airportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
