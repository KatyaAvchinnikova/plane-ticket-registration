package com.example.businesslayernew.dto.ticket;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TicketRequest {

    @NotNull
    private Long flightId;

    @NotNull
    private Long userId;

}
