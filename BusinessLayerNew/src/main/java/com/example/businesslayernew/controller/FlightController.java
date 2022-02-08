package com.example.businesslayernew.controller;

import com.example.businesslayernew.domain.Flight;
import com.example.businesslayernew.dto.flight.FlightDto;
import com.example.businesslayernew.dto.flight.FlightRequest;
import com.example.businesslayernew.mapper.FlightMapper;
import com.example.businesslayernew.service.FlightService;
import com.example.businesslayernew.validator.FlightTimeValidator;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
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
    private final FlightTimeValidator flightTimeValidator;

    @InitBinder("flightRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(flightTimeValidator);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new flight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FlightDto> create(@Valid @RequestBody FlightRequest request) {
        Flight flight = flightMapper.mapToFlight(request);
        FlightDto flightDto = flightMapper.mapToFlightDto(flightService.create(flight));
        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("Read all flights")
    public ResponseEntity<Page<FlightDto>> readAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<FlightDto> flightDtoList = flightService.getAll(pageable)
                                                     .map(flightMapper::mapToFlightDto);
        return new ResponseEntity<>(flightDtoList, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @ApiOperation("Read flight by id")
    public ResponseEntity<FlightDto> readById(@PathVariable Long id) {
        Flight byId = flightService.getById(id);
        FlightDto flightDto = flightMapper.mapToFlightDto(byId);
        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @ApiOperation("Update flight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FlightDto> update(@PathVariable Long id, @Valid @RequestBody FlightRequest request) {
        Flight flight = flightMapper.mapToFlight(request);
        FlightDto flightDto = flightMapper.mapToFlightDto(flightService.update(id, flight));
        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete flight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FlightDto> delete(@PathVariable Long id) {
        flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
