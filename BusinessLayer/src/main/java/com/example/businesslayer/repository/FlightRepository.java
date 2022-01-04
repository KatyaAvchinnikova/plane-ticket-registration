package com.example.businesslayer.repository;

import com.example.businesslayer.domain.Airport;
import com.example.businesslayer.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
