package com.example.businesslayernew.dto.ticket;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TicketRequest {
    @NotNull(message = "Field flight id cannot be empty")
    private Long flightId;
    @NotNull(message = "User id cannot be empty")
    private Long userId;
}
