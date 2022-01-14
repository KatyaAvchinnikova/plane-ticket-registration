package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.Role;
import com.example.businesslayernew.domain.UserEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements TicketRegistrationService<UserEntity, Long>{

    private static final String RESOURSENAME= "User";
    private static final String FIELDNAME = "Id";

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserEntity create(UserEntity user) {
        if(user.getRole() == null){
            user.setRole(Role.USER);
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity readById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));
    }

    @Override
    public List<UserEntity> readAll() {
          return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity update(Long id, @NotNull UserEntity user) {
        user.setId(id);
        if(user.getRole() == null){
            user.setRole(Role.USER);
        }
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {

        userRepository.deleteById(id);
    }

}
