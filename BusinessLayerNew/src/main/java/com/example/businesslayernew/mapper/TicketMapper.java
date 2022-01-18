package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.TicketEntity;
import com.example.businesslayernew.dto.ticket.TicketRequest;
import com.example.businesslayernew.dto.ticket.TicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mappings({
            @Mapping(target = "id",         ignore = true),
            @Mapping(target = "flight",     ignore = true),
            @Mapping(target = "user",       ignore = true)
    })
    TicketEntity mapToTicket(TicketRequest ticketRequestDto);

    @Mappings({
            @Mapping(target = "airportFromName",    source = "flight.airportFrom.name"),
            @Mapping(target = "airportToName",      source = "flight.airportTo.name"),
            @Mapping(target = "userFirstName",      source = "user.firstName"),
            @Mapping(target = "userLastName",       source = "user.lastName"),
            @Mapping(target = "departureTime",      source = "flight.departureTime"),
            @Mapping(target = "arrivalTime",        source = "flight.arrivalTime")
    })
    TicketResponse mapToTicketDto(TicketEntity ticket);
}
