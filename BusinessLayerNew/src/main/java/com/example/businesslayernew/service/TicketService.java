package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.domain.TicketEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.FlightRepository;
import com.example.businesslayernew.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private static final String RESOURSENAME = "Ticket";

    private static final String FIELDNAME = "Id";

    private final TicketRepository ticketRepository;

    @Transactional
    public TicketEntity create(TicketEntity ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }

    public TicketEntity readById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));
    }

    public List<TicketEntity> readAll(int page, int size) {

        Pageable pageSize = PageRequest.of(page - 1, size);

        return ticketRepository.findAll(pageSize).toList();
    }

    @Transactional
    public TicketEntity update(Long id, TicketEntity ticket) {
        Optional.of(ticketRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        ticket.setId(id);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Transactional
    public void delete(Long id) {
        Optional.of(ticketRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        ticketRepository.deleteById(id);
    }

}
