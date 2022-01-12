package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService implements TicketRegistrationService<AirportEntity, Long>{

    private final AirportRepository airportRepository;
    private static final String RESOURSENAME= "Airport";
    private static final String FIELDNAME = "Id";

    @Override
    @Transactional
    public AirportEntity create(AirportEntity airport) {
        airportRepository.save(airport);
        return airport;
    }

    @Override
    public AirportEntity readById(Long id) {
        Optional<AirportEntity> airport = Optional.of(airportRepository.getById(id));
        return airportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));
    }

    @Override
    public List<AirportEntity> readAll() {
        return airportRepository.findAll();
    }

    @Override
    @Transactional
    public AirportEntity update(AirportEntity airport) {
        Optional.of(airportRepository.getById(airport.getId())).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, airport.getId()));
        airportRepository.save(airport);
        return airport;
    }

    @Override
    @Transactional
    public void delete(AirportEntity airport) {
        Optional.of(airportRepository.getById(airport.getId())).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, airport.getId()));
        airportRepository.delete(airport);
    }

}
