package com.example.businesslayer.service;

import com.example.businesslayer.domain.AirportEntity;
import com.example.businesslayer.exception.ResourceNotFoundException;
import com.example.businesslayer.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService implements TicketRegistrationService<AirportEntity, Long>{

    private final AirportRepository airportRepository;

    @Override
    @Transactional
    public AirportEntity create(AirportEntity airport) {
        airportRepository.save(airport);
        return airport;
    }

    @Override
    public AirportEntity readById(Long id) {
        Optional<AirportEntity> airport = Optional.of(airportRepository.getById(id));
        if(airport.isPresent()){
            return airport.get();
        }else{
            throw new ResourceNotFoundException("Airport", "id", id);
        }
    }

    @Override
    public List<AirportEntity> readAll() {
        return airportRepository.findAll();
    }

    @Override
    @Transactional
    public AirportEntity update(AirportEntity airport) {
        Optional.of(airportRepository.getById(airport.getId())).orElseThrow(
                () -> new ResourceNotFoundException("Airport", "id", airport.getId()));
        airportRepository.save(airport);
        return airport;
    }

    @Override
    @Transactional
    public void delete(AirportEntity airport) {
        Optional.of(airportRepository.getById(airport.getId())).orElseThrow(
                () -> new ResourceNotFoundException("Airport", "id", airport.getId()));
        airportRepository.delete(airport);
    }

}
