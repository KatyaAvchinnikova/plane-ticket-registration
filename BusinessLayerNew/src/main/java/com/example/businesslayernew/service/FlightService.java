package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.exception.ArrivalTimeBeforeDepartureTimeException;
import com.example.businesslayernew.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private static final String RESOURSENAME = "Flight";

    private static final String FIELDNAME = "Id";

    private final FlightRepository flightRepository;

    @Transactional
    @Cacheable(value = "flights")
    public FlightEntity create(FlightEntity flight) {
        if (flight.getDepartureTime().isAfter(flight.getArrivalTime())) {
            throw new ArrivalTimeBeforeDepartureTimeException(flight.getAirportFrom().getName(),
                    flight.getAirportTo().getName());
        }
        flightRepository.save(flight);
        return flight;
    }

    @Cacheable(value = "flights")
    public FlightEntity getById(Long id) {
        return flightRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
    }

    public List<FlightEntity> getAll(int page, int size) {

        Pageable pageSize = PageRequest.of(page - 1, size);

        return flightRepository.findAll(pageSize).toList();
    }

    @Transactional
    @CachePut(value = "flights", key = "#flight.id")
    public FlightEntity update(Long id, @NotNull FlightEntity flight) {
        if (flight.getDepartureTime().isAfter(flight.getArrivalTime())) {
            throw new ArrivalTimeBeforeDepartureTimeException(flight.getAirportFrom().getName(),
                    flight.getAirportTo().getName());
        } else if (flightRepository.findById(id) == null) {
            throw new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id);
        } else {
            flight.setId(id);
        }
        return flightRepository.save(flight);
    }

    @Transactional
    @CacheEvict(value = "flights")
    public void delete(Long id) {
        if (flightRepository.findById(id) == null) {
            throw new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id);
        } else {
            flightRepository.deleteById(id);
        }
    }

}
