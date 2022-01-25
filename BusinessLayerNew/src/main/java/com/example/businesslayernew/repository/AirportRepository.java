package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//TODO: Что за чудеса читабельности? JpaRepository наследует PagingAndSortingRepository
@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Long>,
        PagingAndSortingRepository <AirportEntity, Long>{
        Page<AirportEntity> findAll(Pageable pageable);
}
