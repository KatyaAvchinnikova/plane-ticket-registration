package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
//TODO: я буду удалять лишнее наследование?)
public interface FlightRepository extends JpaRepository<Flight, Long>,
        PagingAndSortingRepository<Flight, Long> {

    Page<Flight> findAll(Pageable pageable);

}
