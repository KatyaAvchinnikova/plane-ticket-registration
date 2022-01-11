package com.example.businesslayer.service;

import com.example.businesslayer.domain.FlightEntity;
import com.example.businesslayer.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService implements TicketRegistrationService<FlightEntity, Long>{

    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public FlightEntity create(FlightEntity flight) {
        flightRepository.save(flight);
        return flight;
    }

    @Override
    public FlightEntity readById(Long id) {
        return flightRepository.getById(id);
    }

    @Override
    public List<FlightEntity> readAll() {
        return flightRepository.findAll();
    }

    @Override
    @Transactional
    public FlightEntity update(FlightEntity flight) {
        flightRepository.save(flight);
        return flight;
    }

    @Override
    @Transactional
    public void delete(FlightEntity flight) {
        flightRepository.delete(flight);
    }

}
