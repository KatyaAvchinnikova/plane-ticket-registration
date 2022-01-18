package com.example.businesslayernew.controller;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.dto.airport.AirportRequest;
import com.example.businesslayernew.dto.airport.AirportResponse;
import com.example.businesslayernew.mapper.AirportMapper;
import com.example.businesslayernew.service.AirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//TODO: здесь и далее: зачем геттеры и сеттеры для классов бизнес-логики?
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/airports")
@Api("Airport controller")
public class AirportController {

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    //    TODO: зачем коммент? Если делаем комменты с описанием метода - пишем как доку. Здесь и далее
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new airport")
    public ResponseEntity<AirportResponse> create(@RequestBody AirportRequest request) {

        AirportResponse airportResponseDto =
                airportMapper.mapAirportDto(
                        airportService.create(airportMapper.mapAirport(request)));

//        TODO: лишние пробелы. Юзаем ctrl+alt+L
        return new ResponseEntity<>(airportResponseDto, HttpStatus.CREATED);

    }

    @GetMapping
    @ApiOperation("Read all airports")
    public List<AirportResponse> readAll() {
        //    TODO: одна строчка - одна точка
        return airportService.getAll()
                             .stream()
                             .map((airportMapper::mapAirportDto))
                             .collect(
                                     Collectors.toList());
    }

    //    TODO: потерян слеш
    @GetMapping("/{id}")
    @ApiOperation("Read airport by id")
//    TODO: Вроде как можно не указывать литерал переменной, если он совпадает с наименованием параметра
    public ResponseEntity<AirportResponse> readById(@PathVariable Long id) {
        return new ResponseEntity<>(airportMapper
                .mapAirportDto(airportService.getById(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update airport")
    public ResponseEntity<AirportResponse> update(@PathVariable Long id,
            @RequestBody AirportRequest request) {
//        TODO: что это за чудо кодочитабельности?
        AirportResponse airportResponse = airportMapper
                        .mapAirportDto(airportService.update(id,  airportMapper.mapAirport(request)));
        return new ResponseEntity<>(airportResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    TODO: описания методов с большой буквы
    @ApiOperation("Delete airport")
    public ResponseEntity<AirportResponse> delete(@PathVariable Long id) {
        airportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
