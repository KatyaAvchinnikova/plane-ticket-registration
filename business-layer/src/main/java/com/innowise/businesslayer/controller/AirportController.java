package com.innowise.businesslayer.controller;

import com.innowise.businesslayer.domain.Airport;
import com.innowise.businesslayer.dto.airport.AirportDto;
import com.innowise.businesslayer.dto.airport.AirportRequest;
import com.innowise.businesslayer.mapper.AirportMapper;
import com.innowise.businesslayer.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airports")
@Tag(name = "Airport controller")
public class AirportController {

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new airport")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AirportDto> create(@RequestBody AirportRequest request) {
        Airport airport = airportMapper.mapAirport(request);
        AirportDto airportResponseDto = airportMapper.mapAirportDto(airportService.create(airport));
        return new ResponseEntity<>(airportResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Read all airports")
    public ResponseEntity<Page<AirportDto>> readAll(@ParameterObject Pageable pageable) {
        Page<AirportDto> airportDtoList = airportService.getAll(pageable)
                                                        .map(airportMapper::mapAirportDto);
        return new ResponseEntity<>(airportDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read airport by id")
    public ResponseEntity<AirportDto> readById(@PathVariable Long id) {
        AirportDto airportDto = airportMapper.mapAirportDto(airportService.getById(id));
        return new ResponseEntity<>(airportDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update airport")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AirportDto> update(@PathVariable Long id, @RequestBody AirportRequest request) {
        Airport requestAirport = airportMapper.mapAirport(request);
        Airport dbAirport = airportService.update(id, requestAirport);
        return new ResponseEntity<>(airportMapper.mapAirportDto(dbAirport), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete airport")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AirportDto> delete(@PathVariable Long id) {
        airportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
