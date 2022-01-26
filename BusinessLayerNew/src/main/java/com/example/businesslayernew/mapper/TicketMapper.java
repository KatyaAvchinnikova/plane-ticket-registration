package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.Ticket;
import com.example.businesslayernew.dto.ticket.TicketDto;
import com.example.businesslayernew.dto.ticket.TicketRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mappings({
            @Mapping(target = "id",         ignore = true),
            @Mapping(target = "flight",     ignore = true),
            @Mapping(target = "user",       ignore = true),
            @Mapping(target = "deleted",    ignore = true)
    })
    Ticket mapToTicket(TicketRequest ticketRequestDto);

    @Mappings({
            @Mapping(target = "airportFromName",    source = "flight.airportFrom.name"),
            @Mapping(target = "airportToName",      source = "flight.airportTo.name"),
            @Mapping(target = "userFirstName",      source = "user.firstName"),
            @Mapping(target = "userLastName",       source = "user.lastName"),
            @Mapping(target = "departureTime",      source = "flight.departureTime"),
            @Mapping(target = "arrivalTime",        source = "flight.arrivalTime"),
            @Mapping(target = "deleted",            ignore = true)
    })
    TicketDto mapToTicketDto(Ticket ticket);
}
