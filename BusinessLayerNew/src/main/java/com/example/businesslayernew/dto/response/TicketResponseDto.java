package com.example.businesslayernew.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TicketResponseDto {

    private String airportFromName;

    private String airportToName;

    private String userFirstName;

    private String userLastName;

    private Date date;

}
