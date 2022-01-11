package com.example.businesslayer.controller;

import com.example.businesslayer.domain.AirportEntity;
import com.example.businesslayer.dto.request.AirportRequestDto;
import com.example.businesslayer.dto.response.AirportResponseDto;
import com.example.businesslayer.mapper.AirportEntityToAirportResponseDtoMapper;
import com.example.businesslayer.mapper.AirportRequestDtoToAirportEntityMapper;
import com.example.businesslayer.service.AirportService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping(value = "/api/airports")
public class AirportController {

    private final AirportService airportService;
    private final AirportRequestDtoToAirportEntityMapper airportDtoToAirportEntityMapper;
    private final AirportEntityToAirportResponseDtoMapper airportEntityToAirportDtoMapper;

    //create new airport
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportResponseDto> create(@RequestBody AirportRequestDto request){

        AirportResponseDto airportResponseDto =
                airportEntityToAirportDtoMapper.map(airportService.create(airportDtoToAirportEntityMapper.map(request)));

        return new ResponseEntity<>( airportResponseDto, HttpStatus.CREATED);

    }

    //read all airports
    @GetMapping
    public List<AirportResponseDto> readAll(){
        return airportService
                .readAll()
                .stream()
                .collect(ArrayList::new,
                        ((airportResponseDtoList, airport) -> airportResponseDtoList.add(airportEntityToAirportDtoMapper.map(airport))),
                        ArrayList::addAll);

    }

    //read by id
    @GetMapping
    public AirportResponseDto readById(@RequestParam("id") Long id){
        return airportEntityToAirportDtoMapper.map(airportService.readById(id));
    }

    //update airport





}
