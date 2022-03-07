package com.innowise.businesslayer.repository;

import com.innowise.businesslayer.domain.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Page<Flight> findAll(Pageable pageable);
}
