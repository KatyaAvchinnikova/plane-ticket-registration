package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.Flight;
import com.example.businesslayernew.domain.Ticket;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.FlightRepository;
import com.example.businesslayernew.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
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
public class TicketService {

    private static final String RESOURCE_NAME = "Ticket";

    private static final String FIELD_NAME = "Id";

    private final TicketRepository ticketRepository;

    private final FlightRepository flightRepository;

    @Transactional
//    TODO: Имеет смысл сделать класс константами, хранящими названия кэшей и использовать константы
    @Cacheable(value = "tickets")
    public Ticket create(Ticket ticket) {

        decreaseNumberOfFreeSeats(ticket);

        ticketRepository.save(ticket);

        return ticket;
    }

    @Cacheable(value = "tickets")
    public Ticket readById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME,
                FIELD_NAME, id));
    }

    public Page<Ticket> readAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Transactional
    @CachePut(value = "tickets", key = "#ticket.id")
    public Ticket update(Long id, Ticket ticket) {
        return ticketRepository.findById(id)
                               .map(this::increaseNumberOfFreeSeats)
                               .map(t -> buildOnUpdate(t, ticket))
                               .map(ticketRepository::save)
                               .map(this::decreaseNumberOfFreeSeats)
                               .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, id));
    }

    @Transactional
    @CacheEvict(value = "tickets")
    public void delete(Long id) {
        ticketRepository.findById(id)
                        .map(this::setDeleted)
                        .map(ticketRepository::save)
                        .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, id));
    }

    @Transactional
    public Ticket decreaseNumberOfFreeSeats(Ticket ticket) {
        Flight flight = flightRepository.findById(ticket.getFlightId()).get();
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
