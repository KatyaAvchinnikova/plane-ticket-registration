package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.repository.FlightRepository;
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
    public FlightEntity update(Long id, FlightEntity flight) {
        flightRepository.save(flight);
        return flight;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

}
