package com.example.businesslayernew.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
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
    private Set<Flight> flightsFrom;

    @OneToMany(mappedBy = "airportTo")
    private Set<Flight> flightsTo;

    private LocalDate deleted;
}
