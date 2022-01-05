package com.example.businesslayer.service;

import com.example.businesslayer.domain.Airport;
import com.example.businesslayer.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService implements TicketRegistrationService<Airport, Long>{

    private AirportRepository airportRepository;

    @Override
    public void create(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public Airport readById(Long id) {
        return airportRepository.getById(id);
    }

    @Override
    public List<Airport> readAll() {
        return airportRepository.findAll();
    }

    @Override
    public void update(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public void delete(Airport airport) {
        airportRepository.delete(airport);
    }

}
