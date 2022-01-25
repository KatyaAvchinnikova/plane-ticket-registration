package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.Airport;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService {
    @Autowired
    private final AirportRepository airportRepository;

    private static final String RESOURSENAME = "Airport";

    private static final String FIELDNAME = "Id";

    @Transactional
    @Cacheable(value = "airports")
    public Airport create(Airport airport) {
        return airportRepository.save(airport);
    }

    @Cacheable(value = "airports")
    public Airport getById(Long id) {
        return airportRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
    }

    public Page<Airport> getAll(Pageable pageable) {

        return airportRepository.findAll(pageable);
    }

    @CachePut(value = "airports", key = "#airport.id")
    public Airport update(Long id, Airport airport) {
        return Optional.of(airportRepository.findById(id))
                       .map(it -> buildOnUpdate(id, airport))
                       .map(airportRepository::save)
                       .orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
    }

    @Transactional
    @CacheEvict(value = "airports")
    public void delete(Long id) {
        if (airportRepository.findById(id) == null) {
            throw new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id);
        } else {
            airportRepository.deleteById(id);
        }
    }

    public Airport buildOnUpdate(Long id, Airport airport) {
        airport.setId(id);
        return airport;
    }

}
