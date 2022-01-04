package com.example.businesslayer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "airport_from_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_from_id", insertable = false, updatable = false)
    private Airport airportFrom;

    @Column(name = "airport_to_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_to_id", insertable = false, updatable = false)
    private Airport airportTo;

    @Column(name = "flight_date", nullable = false)
    private Date date;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<Ticket> tickets;



}
