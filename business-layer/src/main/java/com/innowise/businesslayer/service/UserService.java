package com.innowise.businesslayer.service;

import com.innowise.businesslayer.constant.CacheKey;
import com.innowise.businesslayer.constant.CacheName;
import com.innowise.businesslayer.domain.Role;
import com.innowise.businesslayer.domain.User;
import com.innowise.businesslayer.exception.AppException;
import com.innowise.businesslayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String RESOURCE_NAME = "User";
    private static final String FIELD_NAME = "Id";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Cacheable(value = CacheName.USERS)
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return user;
    }

    @Cacheable(value = CacheName.USERS)
    public User getById(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new AppException(String.format("%s not found with %s : '%s'",
                                     RESOURCE_NAME, FIELD_NAME, id), HttpStatus.NOT_FOUND));
    }

    public Page<User> getAll(Boolean isDeleted, Pageable pageable) {
        return !isDeleted ? userRepository.findAllByDeletedNotNull(pageable)
                          : userRepository.findAllByDeletedIsNull(pageable);
    }

    @Transactional
    @CachePut(value = CacheName.USERS, key = CacheKey.USER_ID)
    public User update(Long id, @NotNull User user) {
        user.setId(id);
        userRepository.save(user);
        return user;
    }

    @Transactional
    @CacheEvict(value = CacheName.USERS, key = CacheKey.USER_ID)
    public void delete(Long id) {
        userRepository.findById(id)
                      .map(this::setDeleted)
                      .map(userRepository::save)
                      .orElseThrow(() -> new AppException(String.format("%s not found with %s : '%s'",
                              RESOURCE_NAME, FIELD_NAME, id), HttpStatus.NOT_FOUND));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new AppException(String.format("Email %s is not found", email),
                                     HttpStatus.NOT_FOUND));
    }

    public User findByUserName(String userName) {
        return userRepository.findUserByUserName(userName)
                             .orElseThrow(() -> new AppException(
                                     "User with username: " + userName + " is not found", HttpStatus.NOT_FOUND));
    }

    public User findByUserName(String userName, RuntimeException ex) {
        return userRepository.findUserByUserName(userName)
                             .orElseThrow(() -> ex);
    }

    public User setDeleted(User user) {
        user.setDeleted(LocalDate.now());
        return user;
    }

}