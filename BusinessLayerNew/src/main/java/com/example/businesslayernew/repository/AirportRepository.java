package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Page<Airport> findAll(Pageable pageable);
}
