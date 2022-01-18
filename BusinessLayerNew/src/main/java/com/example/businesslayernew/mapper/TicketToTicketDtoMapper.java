package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.TicketEntity;
import com.example.businesslayernew.dto.ticket.TicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TicketToTicketDtoMapper {
    @Mappings({
            @Mapping(target = "airportFromName",    source = "flight.airportFrom.name"),
            @Mapping(target = "airportToName",      source = "flight.airportTo.name"),
            @Mapping(target = "userFirstName",      source = "user.firstName"),
            @Mapping(target = "userLastName",       source = "user.lastName"),
            @Mapping(target = "date",               source = "flight.date")
    })
    TicketResponse map(TicketEntity ticket);
}
