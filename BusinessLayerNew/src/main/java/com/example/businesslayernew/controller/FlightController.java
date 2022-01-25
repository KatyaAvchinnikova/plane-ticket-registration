package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.flight.FlightRequest;
import com.example.businesslayernew.dto.flight.FlightResponse;
import com.example.businesslayernew.mapper.FlightMapper;
import com.example.businesslayernew.service.FlightService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping( "/api/flights")
@Api("Flights controller")
public class FlightController {

    private final FlightService flightService;

    private final FlightMapper flightMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new flight")
    public ResponseEntity<FlightResponse> create(@Valid @RequestBody FlightRequest request) {
        FlightResponse flightResponse =
                flightMapper.mapToFlightDto(flightService.create(
                        flightMapper.mapToFlight(request)));

        return new ResponseEntity<>(flightResponse, HttpStatus.CREATED);
    }

    @GetMapping(params = {"page", "size"})
    @ApiOperation("Read all flights")
    public List<FlightResponse> readAll(@RequestParam("page") int page,
            @RequestParam("size") int size) {
        return flightService.getAll(page, size).stream().map((flightMapper::mapToFlightDto)).collect(
                Collectors.toList());
    }

//    TODO: ты точно здесь слеши не теряешь? /{id}
    @GetMapping("{id}")
    @ApiOperation("Read flight by id")
    public ResponseEntity<FlightResponse> readById(@PathVariable Long id) {
        return new ResponseEntity<>(flightMapper.mapToFlightDto(flightService.getById(id)), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    @ApiOperation("Update flight")
//    TODO: зачем перенос на новую строку?
    public ResponseEntity<FlightResponse> update(@PathVariable Long id,
            @Valid @RequestBody FlightRequest request) {
//        TODO: лучше уж так
//        FlightResponse flightResponse = flightMapper.mapToFlightDto(
//                flightService.update(id, flightMapper.mapToFlight(request)));
        FlightResponse flightResponse = flightMapper.mapToFlightDto(flightService.update(id,
                flightMapper.mapToFlight(request)));
        return new ResponseEntity<>(flightResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete flight")
    public ResponseEntity<FlightResponse> delete(@PathVariable Long id) {
//        TODO: лишние пустые строчки
        flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
