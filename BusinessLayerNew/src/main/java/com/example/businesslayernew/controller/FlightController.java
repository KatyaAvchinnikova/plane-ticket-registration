package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.flight.FlightDto;
import com.example.businesslayernew.dto.flight.FlightRequest;
import com.example.businesslayernew.mapper.FlightMapper;
import com.example.businesslayernew.service.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping("/api/flights")
@Api("Flights controller")
public class FlightController {

    private final FlightService flightService;

    private final FlightMapper flightMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new flight")
    public FlightDto create(@Valid @RequestBody FlightRequest request) {
        return flightMapper.mapToFlightDto(flightService.create(
                flightMapper.mapToFlight(request)));
    }

    @GetMapping
    @ApiOperation("Read all flights")
    public Page<FlightDto> readAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return flightService.getAll(pageable)
                            .map(flightMapper::mapToFlightDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @ApiOperation("Read flight by id")
    public FlightDto readById(@PathVariable Long id) {

        return flightMapper.mapToFlightDto(flightService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @ApiOperation("Update flight")
    public FlightDto update(@PathVariable Long id, @Valid @RequestBody FlightRequest request) {
        return flightMapper.mapToFlightDto(flightService.update(id,
                flightMapper.mapToFlight(request)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete flight")
    public ResponseEntity<FlightDto> delete(@PathVariable Long id) {
        flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
