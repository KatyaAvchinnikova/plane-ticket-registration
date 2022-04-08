package com.innowise.businesslayer.controller;

import com.innowise.businesslayer.domain.Flight;
import com.innowise.businesslayer.dto.flight.FlightDto;
import com.innowise.businesslayer.dto.flight.FlightRequest;
import com.innowise.businesslayer.mapper.FlightMapper;
import com.innowise.businesslayer.service.FlightService;
import com.innowise.businesslayer.validator.FlightTimeValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/flights")
@Tag(name = "Flights controller")
public class FlightController {

    private final FlightService flightService;
    private final FlightMapper flightMapper;
    private final FlightTimeValidator flightTimeValidator;

    @InitBinder("flightRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(flightTimeValidator);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Create new flight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FlightDto> create(@Valid @RequestBody FlightRequest request) {
        Flight flight = flightMapper.mapToFlight(request);
        FlightDto flightDto = flightMapper.mapToFlightDto(flightService.create(flight));
        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Read all flights")
    public ResponseEntity<Page<FlightDto>> readAll(@ParameterObject Pageable pageable) {
        Page<FlightDto> flightDtoList = flightService.getAll(pageable)
                                                     .map(flightMapper::mapToFlightDto);
        return new ResponseEntity<>(flightDtoList, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Read flight by id")
    public ResponseEntity<FlightDto> readById(@PathVariable Long id) {
        Flight byId = flightService.getById(id);
        FlightDto flightDto = flightMapper.mapToFlightDto(byId);
        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @Operation(summary = "Update flight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FlightDto> update(@PathVariable Long id, @Valid @RequestBody FlightRequest request) {
        Flight flight = flightMapper.mapToFlight(request);
        FlightDto flightDto = flightMapper.mapToFlightDto(flightService.update(id, flight));
        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete flight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FlightDto> delete(@PathVariable Long id) {
        flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
