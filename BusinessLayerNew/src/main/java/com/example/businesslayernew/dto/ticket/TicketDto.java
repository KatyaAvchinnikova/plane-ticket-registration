package com.example.businesslayernew.dto.ticket;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDto {

    private String airportFromName;

    private String airportToName;

    private String userFirstName;

    private String userLastName;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private LocalDate deleted;

}
