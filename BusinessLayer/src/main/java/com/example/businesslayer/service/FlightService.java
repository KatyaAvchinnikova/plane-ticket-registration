package com.example.businesslayer.service;

import com.example.businesslayer.domain.Flight;
import com.example.businesslayer.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements TicketRegistrationService<Flight, Long>{

    private FlightRepository flightRepository;

    @Override
    public void create(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public Flight readById(Long id) {
        return flightRepository.getById(id);
    }

    @Override
    public List<Flight> readAll() {
        return flightRepository.findAll();
    }

    @Override
    public void update(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void delete(Flight flight) {
        flightRepository.delete(flight);
    }

}
