package com.example.businesslayer.repository;

import com.example.businesslayer.domain.Airport;
import com.example.businesslayer.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
