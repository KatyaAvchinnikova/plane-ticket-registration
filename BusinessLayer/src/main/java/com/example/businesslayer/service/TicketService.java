package com.example.businesslayer.service;

import com.example.businesslayer.domain.Ticket;
import com.example.businesslayer.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService implements TicketRegistrationService<Ticket, Long>{

    private TicketRepository ticketRepository;

    @Override
    @Transactional
    public void create(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket readById(Long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public List<Ticket> readAll() {
        return ticketRepository.findAll();
    }

    @Override
    @Transactional
    public void update(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

}