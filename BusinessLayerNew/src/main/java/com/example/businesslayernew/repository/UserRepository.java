package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long>{
    Page<UserEntity> findAllByDeletedIsNull(Pageable pageable);
    Page<UserEntity> findAllByDeletedNotNull(Pageable pageable);
}
