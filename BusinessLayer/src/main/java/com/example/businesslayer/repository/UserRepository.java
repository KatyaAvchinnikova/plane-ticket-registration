package com.example.businesslayer.repository;

import com.example.businesslayer.domain.Airport;
import com.example.businesslayer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
