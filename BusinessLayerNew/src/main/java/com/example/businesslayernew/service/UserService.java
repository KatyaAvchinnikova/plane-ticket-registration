package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.Role;
import com.example.businesslayernew.domain.UserEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements TicketRegistrationService<UserEntity, Long>{

    private static final String RESOURSENAME= "User";

    private static final String FIELDNAME = "Id";

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

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
        return null;
    }

    public List<UserEntity> readAllWithFilter(boolean isDeleted) {

        Session session = entityManager.unwrap(Session.class);

        Filter filter = session.enableFilter("deletedUserFilter");
        filter.setParameter("deletedOnly", isDeleted);

        List<UserEntity> all = userRepository.findAll();

        session.disableFilter("deletedUserFilter");

        return all;
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
