package com.example.businesslayer.service;

import java.util.List;
import javax.persistence.Entity;

public interface TicketRegistrationService<Entity,Key>{
    Entity create(Entity entity);
    Entity readById(Key key);
    List<Entity> readAll();
    Entity update(Entity entity);
    void delete(Entity entity);
}
