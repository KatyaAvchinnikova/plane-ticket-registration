package com.innowise.businesslayer.service;

import com.innowise.businesslayer.constant.CacheKey;
import com.innowise.businesslayer.constant.CacheName;
import com.innowise.businesslayer.domain.Flight;
import com.innowise.businesslayer.domain.Ticket;
import com.innowise.businesslayer.exception.AppException;
import com.innowise.businesslayer.repository.FlightRepository;
import com.innowise.businesslayer.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private static final String RESOURCE_NAME = "Ticket";
    private static final String FIELD_NAME = "Id";
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    @Transactional
    @Cacheable(value = CacheName.TICKETS)
    public Ticket create(Ticket ticket) {
        Flight flight = flightRepository.findById(ticket.getFlightId())
                                        .orElseThrow(() -> new AppException("Flight with id " + ticket.getFlightId()
                                                + " is not found.", HttpStatus.NOT_FOUND));
        ticket.setFlight(flight);
        decreaseNumberOfFreeSeats(ticket);

        ticketRepository.save(ticket);

        return ticket;
    }

    @Cacheable(value = CacheName.TICKETS)
    public Ticket readById(Long id) {
        return ticketRepository.findById(id)
                               .orElseThrow(() -> new AppException(String.format("%s not found with %s : '%s'",
                                       RESOURCE_NAME, FIELD_NAME, id), HttpStatus.NOT_FOUND));
    }

    public Page<Ticket> readAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Transactional
    @CachePut(value = CacheName.TICKETS, key = CacheKey.TICKET_ID)
    public Ticket update(Long id, Ticket ticket) {
        return ticketRepository.findById(id)
                               .map(this::increaseNumberOfFreeSeats)
                               .map(t -> buildOnUpdate(t, ticket))
                               .map(ticketRepository::save)
                               .map(this::decreaseNumberOfFreeSeats)
                               .orElseThrow(() -> new AppException(String.format("%s not found with %s : '%s'",
                                       RESOURCE_NAME, FIELD_NAME, id), HttpStatus.NOT_FOUND));
    }

    @Transactional
    @CacheEvict(value = CacheName.TICKETS, key = CacheKey.TICKET_ID)
    public void delete(Long id) {
        ticketRepository.findById(id)
                        .map(this::setDeleted)
                        .map(ticketRepository::save)
                        .orElseThrow(() -> new AppException(String.format("%s not found with %s : '%s'",
                                RESOURCE_NAME, FIELD_NAME, id), HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Ticket decreaseNumberOfFreeSeats(Ticket ticket) {
        Flight flight = flightRepository.findById(ticket.getFlightId())
                                        .orElseThrow(() -> new AppException(String.format("%s not found with %s : '%s'",
                                                "Flight", "id", ticket.getFlightId()), HttpStatus.NOT_FOUND));
        String airportFrom = flight.getAirportFrom().getName();
        String airportTo = flight.getAirportFrom().getName();
        String timeDeparture = flight.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (flight.getNumberOfFreeSeats() == 0) {
            throw new AppException(
                    String.format("No free seats on flight from %s to %s time departure: %s", airportFrom,
                            airportTo, timeDeparture), HttpStatus.BAD_REQUEST);
        }

        flight.setNumberOfFreeSeats(flight.getNumberOfFreeSeats() - 1);
        flightRepository.save(flight);
        return ticket;
    }

    @Transactional
    public Ticket increaseNumberOfFreeSeats(Ticket ticket) {
        Flight flight = flightRepository.findById(ticket.getFlightId()).get();
        flight.setNumberOfFreeSeats(flight.getNumberOfFreeSeats() + 1);
        flightRepository.save(flight);
        return ticket;
    }

    public Ticket buildOnUpdate(Ticket dbTicket, Ticket requestTicket) {
        dbTicket.setFlight(requestTicket.getFlight());
        dbTicket.setUser(requestTicket.getUser());
        return dbTicket;
    }

    public Ticket setDeleted(Ticket ticket) {
        ticket.setDeleted(LocalDate.now());
        return ticket;
    }
}
