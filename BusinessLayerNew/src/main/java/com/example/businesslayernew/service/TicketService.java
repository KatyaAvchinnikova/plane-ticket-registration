package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.Flight;
import com.example.businesslayernew.domain.Ticket;
import com.example.businesslayernew.exception.NoFreeSeatsException;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.FlightRepository;
import com.example.businesslayernew.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
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
public class TicketService {

    private static final String RESOURSENAME = "Ticket";

    private static final String FIELDNAME = "Id";

    private final TicketRepository ticketRepository;

    private final FlightRepository flightRepository;

    @Transactional
    @Cacheable(value = "tickets")
    public Ticket create(Ticket ticket) {

        decreaseNumberOfFreeSeats(ticket);

        ticketRepository.save(ticket);

        return ticket;
    }

    @Cacheable(value = "tickets")
    public Ticket readById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));
    }

    public Page<Ticket> readAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Transactional
    @CachePut(value = "tickets", key = "#ticket.id")
    public Ticket update(Long id, Ticket ticket) {
        if (ticketRepository.findById(id) == null) {
            throw new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id);
        } else {
            increaseNumberOfFreeSeats(id);
            ticket.setId(id);
            ticketRepository.save(ticket);
            decreaseNumberOfFreeSeats(ticket);
        }//TODO: пустая строка после иф-элз
        return ticket;
    }

    @Transactional
    @CacheEvict(value = "tickets")
    public void delete(Long id) {
        if (ticketRepository.findById(id) == null) {
            throw new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id);
        } else {
            increaseNumberOfFreeSeats(id);
            ticketRepository.deleteById(id);
        }
    }

    public void decreaseNumberOfFreeSeats(Ticket ticket) {
//TODO: что за чудеса обработки опшнл?
        Flight flight = flightRepository.findById(ticket.getFlightId()).get();
        if (flight == null) {
            throw new ResourceNotFoundException("Flight", "id", ticketRepository.getById(ticket.getId()).getFlightId());
        }

        int numberOfFreeSeats = flight.getNumberOfFreeSeats();
//TODO: про уровень валидации уже говорил
        if (numberOfFreeSeats == 0) {
            throw new NoFreeSeatsException(flight.getAirportFrom().getName(), flight.getAirportTo().getName(),
                    flight.getDepartureTime());
        } else {
            flight.setNumberOfFreeSeats(numberOfFreeSeats - 1);
            flightRepository.save(flight);
        }
    }

    public void increaseNumberOfFreeSeats(Long id) {

        Flight flight = flightRepository.getById(ticketRepository.getById(id).getFlightId());

        if (flight == null) {
            throw new ResourceNotFoundException("Flight", "id", ticketRepository.getById(id).getFlightId());
        } else {
            int numberOfFreeSeats = flight.getNumberOfFreeSeats();
            flight.setNumberOfFreeSeats(numberOfFreeSeats + 1);
            flightRepository.save(flight);
        }
    }

}
