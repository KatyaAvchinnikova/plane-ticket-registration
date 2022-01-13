package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.UserEntity;
import com.example.businesslayernew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements TicketRegistrationService<UserEntity, Long>{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserEntity create(UserEntity user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity readById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<UserEntity> readAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity update(Long id, UserEntity user) {
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
