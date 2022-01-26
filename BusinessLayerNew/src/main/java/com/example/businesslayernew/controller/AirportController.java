package com.example.businesslayernew.controller;

//TODO: всегда перед коммитов приводи код в соответствие с код-стайлом и чисти импорты. ctrl+alt+L

import com.example.businesslayernew.domain.Airport;
import com.example.businesslayernew.dto.airport.AirportDto;
import com.example.businesslayernew.dto.airport.AirportRequest;
import com.example.businesslayernew.mapper.AirportMapper;
import com.example.businesslayernew.service.AirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//TODO: здесь и далее: зачем геттеры и сеттеры для классов бизнес-логики?
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airports")
@Api("Airport controller")
public class AirportController {

    private final AirportService airportService;

    private final AirportMapper airportMapper;

    @InitBinder
    public void init() {

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new airport")
    public ResponseEntity<AirportDto> create(@RequestBody AirportRequest request) {
        Airport airport = airportMapper.mapAirport(request);
        AirportDto airportResponseDto = airportMapper.mapAirportDto(airportService.create(airport));
        return new ResponseEntity(airportResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("Read all airports")
    @ResponseStatus(HttpStatus.OK)
    public Page<AirportDto> readAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {

        return airportService.getAll(pageable)
                             .map(airportMapper::mapAirportDto);
    }

    @GetMapping("/{id}")
    @ApiOperation("Read airport by id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AirportDto> readById(@PathVariable Long id) {
        return new ResponseEntity<>(airportMapper.mapAirportDto(airportService.getById(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update airport")
    public ResponseEntity<AirportDto> update(@PathVariable Long id, @RequestBody AirportRequest request) {
        Airport requestAirport = airportMapper.mapAirport(request);
        Airport dbAirport = airportService.update(id, requestAirport);
        return new ResponseEntity<>(airportMapper.mapAirportDto(dbAirport), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete airport")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AirportDto> delete(@PathVariable Long id) {
        airportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
