package com.example.businesslayernew.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//TODO: Здесь и далее: зачем слово Entity? Убираем и даже @Table  yt ye;ty
//TODO: Здесь и далее: зачем слово Entity? (х2)
@Table(name="airport")
public class AirportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "airportFrom")
    private Set<FlightEntity> flightsFrom;

    @OneToMany(mappedBy = "airportTo")
    private Set<FlightEntity> flightsTo;
//    TODO: лишняя пустая строка

}
