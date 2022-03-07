package com.innowise.businesslayer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    TODO: Если связанных сущностей нет, поле будет null или пустая коллекция? Если первое - добавить инициализацию по дефолту
//    Будет пустая коллекция, проверила!
    @OneToMany(mappedBy = "airportFrom")
    private List<Flight> flightsFrom = new ArrayList<>();

    @OneToMany(mappedBy = "airportTo")
    private List<Flight> flightsTo = new ArrayList<>();

    private LocalDate deleted;
}
