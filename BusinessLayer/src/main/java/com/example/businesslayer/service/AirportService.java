package com.example.businesslayer.service;

import com.example.businesslayer.domain.AirportEntity;
import com.example.businesslayer.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return airportRepository.getById(id);
    }

    @Override
    public List<AirportEntity> readAll() {
        return airportRepository.findAll();
    }

    @Override
    @Transactional
    public AirportEntity update(AirportEntity airport) {
        airportRepository.save(airport);
        return airport;
    }

    @Override
    @Transactional
    public void delete(AirportEntity airport) {
        airportRepository.delete(airport);
    }

}
