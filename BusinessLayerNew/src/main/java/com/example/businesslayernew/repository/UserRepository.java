package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByDeletedIsNull();
    List<UserEntity> findAllByDeletedNotNull();
}
