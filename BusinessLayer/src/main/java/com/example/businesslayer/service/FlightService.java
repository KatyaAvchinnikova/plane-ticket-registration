package com.example.businesslayer.service;

import com.example.businesslayer.domain.Flight;
import com.example.businesslayer.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService implements TicketRegistrationService<Flight, Long>{

    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public Flight create(Flight flight) {
        flightRepository.save(flight);
        return flight;
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
    @Transactional
    public Flight update(Flight flight) {
        flightRepository.save(flight);
        return flight;
    }

    @Override
    @Transactional
    public void delete(Flight flight) {
        flightRepository.delete(flight);
    }

}
