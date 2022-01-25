package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//TODO: Что за чудеса читабельности? JpaRepository наследует PagingAndSortingRepository
@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Page<Airport> findAll(Pageable pageable);

}
