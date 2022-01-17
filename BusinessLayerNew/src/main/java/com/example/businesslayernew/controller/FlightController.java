package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.request.FlightRequestDto;
import com.example.businesslayernew.dto.response.FlightResponseDto;
import com.example.businesslayernew.mapper.FlightEntityToFlightResponseDtoMapper;
import com.example.businesslayernew.mapper.FlightRequestDtoToFlightEntityMapper;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping(value = "/api/flights")
@Api("Flights controller")
public class FlightController {
    private final FlightService flightService;
    private final FlightEntityToFlightResponseDtoMapper flightEntityToFlightResponseDtoMapper;
    private final FlightRequestDtoToFlightEntityMapper flightRequestDtoToFlightEntityMapper;

    //create new flight
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new flight")
    public ResponseEntity<FlightResponseDto> create(@RequestBody FlightRequestDto request){
        FlightResponseDto flightResponseDto =
                flightEntityToFlightResponseDtoMapper.map(flightService.create(
                        flightRequestDtoToFlightEntityMapper.map(request)));

        return new ResponseEntity<>( flightResponseDto, HttpStatus.CREATED);
    }

    //read all airports
    @GetMapping
    @ApiOperation("read all flights")
    public List<FlightResponseDto> readAll(){
       return flightService.readAll().stream().map((flightEntityToFlightResponseDtoMapper::map)).collect(
                Collectors.toList());

    }

    //read by id
    @GetMapping("{id}")
    @ApiOperation("read flight by id")
    public ResponseEntity<FlightResponseDto> readById(@PathVariable("id") Long id){
        return new ResponseEntity<>(flightEntityToFlightResponseDtoMapper.map(flightService.readById(id)), HttpStatus.OK);
    }

    //update flight
    @PatchMapping("{id}")
    @ApiOperation("update flight")
    public ResponseEntity<FlightResponseDto> update(@PathVariable("id") Long id, @RequestBody FlightRequestDto request){
        FlightResponseDto flightResponse = flightEntityToFlightResponseDtoMapper.map(flightService.update(id,
                flightRequestDtoToFlightEntityMapper.map(request)));
        return new ResponseEntity<>(flightResponse, HttpStatus.OK);
    }

    //delete airport
    @DeleteMapping("{id}")
    @ApiOperation("delete flight")
    public ResponseEntity<FlightResponseDto> delete(@PathVariable("id") Long id){
//        TODO: лишние пустые строчки

        flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
