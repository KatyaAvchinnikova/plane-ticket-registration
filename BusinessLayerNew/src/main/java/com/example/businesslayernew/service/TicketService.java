package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.domain.TicketEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.FlightRepository;
import com.example.businesslayernew.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService implements TicketRegistrationService<TicketEntity, Long>{
    private static final String RESOURSENAME= "Ticket";
    private static final String FIELDNAME = "Id";

    private final TicketRepository ticketRepository;

    @Override
    @Transactional
    public TicketEntity create(TicketEntity ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public TicketEntity readById(Long id) {

        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));
    }

    @Override
    public List<TicketEntity> readAll() {

        return ticketRepository.findAll();
    }

    @Override
    @Transactional
    public TicketEntity update(Long id, TicketEntity ticket) {
        Optional.of(ticketRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        ticket.setId(id);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional.of(ticketRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        ticketRepository.deleteById(id);
    }

}
