package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.Role;
import com.example.businesslayernew.domain.User;
import com.example.businesslayernew.exception.NoUserEmailException;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String RESOURCE_NAME = "User";

    private static final String FIELD_NAME = "Id";

    private final UserRepository userRepository;

    @Transactional
    @Cacheable(value = "users")
    public User create(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        userRepository.save(user);
        return user;
    }

    @Cacheable(value = "users")
    public User getById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME,
                FIELD_NAME, id));
    }

    public Page<User> getAll(Boolean isDeleted, Pageable pageable) {
        return !isDeleted ? userRepository.findAllByDeletedNotNull(pageable)
                          : userRepository.findAllByDeletedIsNull(pageable);
    }

    @Transactional
    @CachePut(value = "users", key = "#user.id")
    public User update(Long id, @NotNull User user) {
        user.setId(id);
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        userRepository.save(user);
        return user;
    }

    @Transactional
    @CacheEvict(value = "users")
    public void delete(Long id) {
        userRepository.findById(id)
                      .map(this::setDeleted)
                      .map(userRepository::save)
                      .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, id));
    }

    public User setDeleted(User user) {
        user.setDeleted(LocalDate.now());
        return user;
    }

    public User findByEmail(String email){
       return userRepository.findByEmail(email).orElseThrow(() -> new NoUserEmailException(email));
    }

}
