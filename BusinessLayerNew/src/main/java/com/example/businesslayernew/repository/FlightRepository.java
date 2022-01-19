package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.domain.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long>, PagingAndSortingRepository <FlightEntity,
        Long> {
        Page<FlightEntity> findAll(Pageable pageable);
}
