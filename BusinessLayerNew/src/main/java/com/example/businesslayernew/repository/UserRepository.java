package com.example.businesslayernew.repository;

import com.example.businesslayernew.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByDeletedIsNull(Pageable pageable);

    Page<User> findAllByDeletedNotNull(Pageable pageable);

    Optional<User> findByEmail(String email);

    Optional<User> findUserByUserName(String userName);
}
