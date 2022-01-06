package com.example.businesslayer.controller;

import com.example.businesslayer.domain.Airport;
import com.example.businesslayer.service.AirportService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping(value = "/airports")
public class AirportController {

    private AirportService airportService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody Airport airport){
        airportService.create(airport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
