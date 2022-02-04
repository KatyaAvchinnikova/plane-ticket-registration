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

    @OneToMany(mappedBy = "airportFrom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Flight> flightsFrom;

    @OneToMany(mappedBy = "airportTo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Flight> flightsTo;

    private LocalDate deleted;
}
