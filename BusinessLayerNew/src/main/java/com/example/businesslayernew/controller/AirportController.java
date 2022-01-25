package com.example.businesslayernew.controller;

//TODO: всегда перед коммитов приводи код в соответствие с код-стайлом и чисти импорты. ctrl+alt+L
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//TODO: здесь и далее: зачем геттеры и сеттеры для классов бизнес-логики?
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airports")
@Api("Airport controller")
public class AirportController {

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    //    TODO: зачем коммент? Если делаем комменты с описанием метода - пишем как доку. Здесь и далее
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new airport")
    public AirportResponse create(@RequestBody AirportRequest request) {

        AirportResponse airportResponseDto = airportMapper.mapAirportDto(airportService
                .create(airportMapper.mapAirport(request)));
//        TODO: лишние пробелы. Юзаем ctrl+alt+L
        return airportResponseDto;
    }

    @GetMapping(params = {"page", "size"})
    @ApiOperation("Read all airports")
    @ResponseStatus(HttpStatus.OK)
    public List<AirportResponse> readAll(@RequestParam("page") int page,
            @RequestParam("size") int size) {
        //    TODO: одна строчка - одна точка
        return airportService.getAll(page, size)
                             .stream()
                             .map((airportMapper::mapAirportDto))
                             .collect(Collectors.toList());
    }

    //    TODO: потерян слеш
    @GetMapping("/{id}")
    @ApiOperation("Read airport by id")
    @ResponseStatus(HttpStatus.OK)
//    TODO: Вроде как можно не указывать литерал переменной, если он совпадает с наименованием параметра
    public ResponseEntity<AirportResponse> readById(@PathVariable Long id) {
//        TODO: выглядит не очень. Либо дто в переменную и ее в конструктор, либо в одну строку
        return new ResponseEntity<>(airportMapper
                .mapAirportDto(airportService.getById(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update airport")
    @ResponseStatus(HttpStatus.CREATED)
    public AirportResponse update(@PathVariable Long id,
            @RequestBody AirportRequest request) {
//        TODO: что это за чудо кодочитабельности?
//        TODO: ни одной точки на строке:)
        AirportResponse airportResponse = airportMapper
                .mapAirportDto(airportService.update(id, airportMapper.mapAirport(request)));
        return airportResponse;
    }

    @DeleteMapping("/{id}")
//    TODO: описания методов с большой буквы
    @ApiOperation("Delete airport")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AirportResponse> delete(@PathVariable Long id) {
        airportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
