package com.innowise.businesslayer.repository;

import com.innowise.businesslayer.domain.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Page<Airport> findAll(Pageable pageable);
}
