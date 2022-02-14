package com.innowise.businesslayer.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheKey {

    public static final String AIRPORTS_ID = "#airport.id";
    public static final String FLIGHT_ID = "#flight.id";
    public static final String TICKET_ID = "#ticket.id";
    public static final String USER_ID = "#user.id";

}
