package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.domain.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long>, PagingAndSortingRepository<TicketEntity,
        Long> {
        Page<TicketEntity> findAll(Pageable pageable);
}
