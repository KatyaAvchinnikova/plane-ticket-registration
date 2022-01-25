package com.example.businesslayernew.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    TODO: в чем смысл optional = false? х2
    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id", insertable = false, updatable = false)
    private FlightEntity flight;

//    TODO: хиб тебе сам не перебьет подобный нэйминг? х2
    @Column(name = "flight_id", nullable = false)
    @NotNull
    private Long flightId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "user_id")
//    TODO: про валидации уже указал выше
    @NotNull
    private Long userId;

}
