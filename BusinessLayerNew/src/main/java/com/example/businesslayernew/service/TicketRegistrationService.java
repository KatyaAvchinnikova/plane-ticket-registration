package com.example.businesslayernew.service;

import java.util.List;
import javax.persistence.Entity;

//TODO: зачем этот интерфейс?
public interface TicketRegistrationService<Entity,Key>{
    Entity create(Entity entity);
    Entity readById(Key key);
    List<Entity> readAll();
    Entity update(Key key, Entity entity);
    void delete(Key key);
}
