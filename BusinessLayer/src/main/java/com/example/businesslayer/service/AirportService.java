package com.example.businesslayer.service;

import com.example.businesslayer.domain.Airport;
import com.example.businesslayer.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService implements TicketRegistrationService<Airport, Long>{

    private AirportRepository airportRepository;

    @Override
    @Transactional
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
    @Transactional
    public void update(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    @Transactional
    public void delete(Airport airport) {
        airportRepository.delete(airport);
    }

}
