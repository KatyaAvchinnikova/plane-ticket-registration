package com.example.businesslayernew.controller;

import com.example.businesslayernew.mapper.AirportEntityToAirportResponseDtoMapper;
import com.example.businesslayernew.mapper.AirportRequestDtoToAirportEntityMapper;
import com.example.businesslayernew.service.AirportService;
import com.example.businesslayernew.service.FlightService;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping(value = "/api/flights")
@Api("Flights controller")
public class FlightController {
    private final FlightService flightService;
    private final AirportRequestDtoToAirportEntityMapper airportDtoToAirportEntityMapper;
    private final AirportEntityToAirportResponseDtoMapper airportEntityToAirportDtoMapper;
}
