package com.example.businesslayernew.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "number_of_free_seats")
    private int numberOfFreeSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_from_id", insertable = false, updatable = false)
    private Airport airportFrom;

//    TODO: доступность сеттера - NONE для таких полей
    @Column(name = "airport_from_id", nullable = false)
    private Long airportFromId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_to_id", insertable = false, updatable = false)
    private Airport airportTo;

    @Column(name = "airport_to_id", nullable = false)
    private Long airportToId;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<Ticket> tickets;
}
