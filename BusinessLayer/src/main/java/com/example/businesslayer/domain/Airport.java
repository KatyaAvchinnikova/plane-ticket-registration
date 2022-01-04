package com.example.businesslayer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    private long id;

    private String name;

    @OneToMany(mappedBy = "airportFrom")
    private Set<Flight> flightsFrom;

    @OneToMany(mappedBy = "airportTo")
    private Set<Flight> flightsTo;

}
