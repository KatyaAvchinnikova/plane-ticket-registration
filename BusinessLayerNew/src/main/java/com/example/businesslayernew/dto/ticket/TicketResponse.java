package com.example.businesslayernew.dto.ticket;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class TicketResponse {

    private String airportFromName;

    private String airportToName;

    private String userFirstName;

    private String userLastName;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

}
