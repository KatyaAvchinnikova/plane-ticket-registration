package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.Flight;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private static final String RESOURCE_NAME = "Flight";

    private static final String FIELD_NAME = "Id";

    private final FlightRepository flightRepository;

    @Transactional
//    TODO: точно та анноташка?
    //Точно
    @Cacheable(value = "flights")
    public Flight create(Flight flight) {
        return flightRepository.save(flight);
    }

    @Cacheable(value = "flights")
    public Flight getById(Long id) {
        return flightRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, id));
    }

    public Page<Flight> getAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    @Transactional
    @CachePut(value = "flights", key = "#flight.id")
    public Flight update(Long id, @NotNull Flight flight) {
        return flightRepository.findById(id)
                               .map(dbFlight -> buildOnUpdate(dbFlight, flight))
                               .map(flightRepository::save)
                               .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, id));
    }

    @Transactional
    @CacheEvict(value = "flights", key = "#flight.id")
    public void delete(Long id) {
        flightRepository.findById(id)
                        .map(this::setDeleted)
                        .map(flightRepository::save)
                        .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, id));
    }

    public Flight buildOnUpdate(Flight dbFlight, Flight requestFlight) {
        dbFlight.setArrivalTime(requestFlight.getArrivalTime());
        dbFlight.setDepartureTime(requestFlight.getDepartureTime());
        dbFlight.setAirportFrom(requestFlight.getAirportFrom());
        dbFlight.setAirportFrom(requestFlight.getAirportTo());
        dbFlight.setNumberOfFreeSeats(requestFlight.getNumberOfFreeSeats());
        return dbFlight;
    }

    public Flight setDeleted(Flight flight) {
        flight.setDeleted(LocalDate.now());
        return flight;
    }

}
