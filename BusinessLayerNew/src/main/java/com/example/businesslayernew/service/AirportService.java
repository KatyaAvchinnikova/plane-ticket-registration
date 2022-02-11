package com.example.businesslayernew.service;

import com.example.businesslayernew.cacheProperty.CacheKey;
import com.example.businesslayernew.cacheProperty.CacheName;
import com.example.businesslayernew.domain.Airport;
import com.example.businesslayernew.exception.AppException;
import com.example.businesslayernew.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private static final String RESOURCE_NAME = "Airport";
    private static final String FIELD_NAME = "Id";

    @Transactional
    @Cacheable(value = "airports")
    public Airport create(Airport airport) {
        return airportRepository.save(airport);
    }

    @Cacheable(value = CacheName.AIRPORTS)
    public Airport getById(Long id) {
        return airportRepository.findById(id)
                                .orElseThrow(
                                        () -> new AppException(String.format("%s not found with %s : '%s'",
                                                RESOURCE_NAME, FIELD_NAME, id), HttpStatus.NOT_FOUND));
    }

    public Page<Airport> getAll(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    @CachePut(value = CacheName.AIRPORTS, key = CacheKey.AIRPORTS_ID)
    @Transactional
    public Airport update(Long id, Airport airport) {
        return airportRepository.findById(id)
                                .map(dbAirport -> buildRequestAirport(dbAirport, airport))
                                .map(airportRepository::save)
                                .orElseThrow(() -> new AppException(String.format("%s not found with %s : '%s'",
                                        RESOURCE_NAME, FIELD_NAME, id), HttpStatus.NOT_FOUND));
    }

    @Transactional
    @CacheEvict(value = CacheName.AIRPORTS)
    public void delete(Long id) {
        airportRepository.findById(id)
                         .map(this::setDeleted)
                         .map(airportRepository::save)
                         .orElseThrow(() -> new AppException(String.format("%s not found with %s : '%s'",
                                 RESOURCE_NAME, FIELD_NAME, id), HttpStatus.NOT_FOUND));
    }

    private Airport buildRequestAirport(Airport dbAirport, Airport requestAirport) {
        dbAirport.setName(requestAirport.getName());
        return dbAirport;
    }

    public Airport setDeleted(Airport airport) {
        airport.setDeleted(LocalDate.now());
        return airport;
    }

}
