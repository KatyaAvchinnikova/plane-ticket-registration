package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.AirportRepository;
import com.example.businesslayernew.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService implements TicketRegistrationService<FlightEntity, Long>{

    private static final String RESOURSENAME= "Flight";
    private static final String FIELDNAME = "Id";

    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public FlightEntity create(FlightEntity flight) {
        flightRepository.save(flight);
        return flight;
    }

    @Override
    public FlightEntity readById(Long id) {

        return flightRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));

    }

    @Override
    public List<FlightEntity> readAll() {
        return flightRepository.findAll();
    }

    @Override
    @Transactional
    public FlightEntity update(Long id, @NotNull FlightEntity flight) {
        Optional.of(flightRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        flight.setId(id);
        flightRepository.save(flight);
        return flight;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional.of(flightRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        flightRepository.deleteById(id);
    }

}