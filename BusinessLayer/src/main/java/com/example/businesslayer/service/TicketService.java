package com.example.businesslayer.service;

import com.example.businesslayer.domain.TicketEntity;
import com.example.businesslayer.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService implements TicketRegistrationService<TicketEntity, Long>{

    private final TicketRepository ticketRepository;

    @Override
    @Transactional
    public TicketEntity create(TicketEntity ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public TicketEntity readById(Long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public List<TicketEntity> readAll() {
        return ticketRepository.findAll();
    }

    @Override
    @Transactional
    public TicketEntity update(TicketEntity ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    @Transactional
    public void delete(TicketEntity ticket) {
        ticketRepository.delete(ticket);
    }

}
