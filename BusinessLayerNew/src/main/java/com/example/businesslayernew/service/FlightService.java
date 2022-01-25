package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.Flight;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.exception.ArrivalTimeBeforeDepartureTimeException;
import com.example.businesslayernew.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService {
//TODO: нэйминг
    private static final String RESOURSENAME = "Flight";

    private static final String FIELDNAME = "Id";

    private final FlightRepository flightRepository;

    @Transactional
//    TODO: точно та анноташка?
    @Cacheable(value = "flights")
    public Flight create(Flight flight) {
        if (flight.getDepartureTime().isAfter(flight.getArrivalTime())) {
            throw new ArrivalTimeBeforeDepartureTimeException(flight.getAirportFrom().getName(),
                    flight.getAirportTo().getName());
        }//TODO: пустая строка, читабельность иф-блока+код после - околонулевая
        flightRepository.save(flight);
        return flight;
    }

    @Cacheable(value = "flights")
    public Flight getById(Long id) {
        return flightRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
    }

    public Page<Flight> getAll(Pageable pageable) {

        return flightRepository.findAll(pageable);
    }

    @Transactional
    @CachePut(value = "flights", key = "#flight.id")
    public Flight update(Long id, @NotNull Flight flight) {
//        TODO: валидация должна быт ьна уровне контроллера
        if (flight.getDepartureTime().isAfter(flight.getArrivalTime())) {
            throw new ArrivalTimeBeforeDepartureTimeException(flight.getAirportFrom().getName(),
                    flight.getAirportTo().getName());
//            TODO: у тебя метод возващает опшнл, тут не мб налл
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
//        TODO: опшнл из репы
//        var d = flightRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME,
//                id))
//                .
        if (flightRepository.findById(id) == null) {
            throw new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id);
        } else {
            flightRepository.deleteById(id);
        }
    }

}
