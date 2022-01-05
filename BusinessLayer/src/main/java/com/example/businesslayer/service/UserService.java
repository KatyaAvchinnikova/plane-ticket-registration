package com.example.businesslayer.service;

import com.example.businesslayer.domain.User;
import com.example.businesslayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements TicketRegistrationService<User, Long>{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User readById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

}
