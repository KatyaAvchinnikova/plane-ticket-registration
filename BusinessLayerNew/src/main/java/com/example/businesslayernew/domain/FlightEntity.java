package com.example.businesslayernew.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="flight")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_from_id", insertable = false, updatable = false)
    private AirportEntity airportFrom;

//    TODO: доступность сеттера - NONE для таких полей
    @Column(name = "airport_from_id", nullable = false)
    private Long airportFromId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_to_id", insertable = false, updatable = false)
    private AirportEntity airportTo;

    @Column(name = "airport_to_id", nullable = false)
    private Long airportToId;

    @Column(name = "flight_date", nullable = false)
//    TODO: LocalDateTime/ZonedDateTime, никаких Date
    private Date date;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<TicketEntity> tickets;



}
