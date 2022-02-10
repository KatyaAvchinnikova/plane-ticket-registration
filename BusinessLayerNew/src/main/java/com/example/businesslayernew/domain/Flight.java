package com.example.businesslayernew.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "number_of_free_seats")
    private int numberOfFreeSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_from_id", nullable = false)
    private Airport airportFrom;

//TODO: Уже писал. Доступность сеттера - NONE
// Это поле мне нужно при маппинге, сеттер тут необходим
    @Column(name = "airport_from_id", insertable = false, updatable = false)
    private Long airportFromId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_to_id", nullable = false)
    private Airport airportTo;

    @Column(name = "airport_to_id", insertable = false, updatable = false)
    private Long airportToId;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets;

    @Column
    private LocalDate deleted;
}
