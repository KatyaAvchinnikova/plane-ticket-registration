package com.innowise.businesslayer.mapper;

import com.innowise.businesslayer.domain.Flight;
import com.innowise.businesslayer.dto.flight.FlightRequest;
import com.innowise.businesslayer.dto.flight.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface FlightMapper {
    @Mappings({
            @Mapping(target = "airportFrom", source = "airportFrom.name"),
            @Mapping(target = "airportTo", source = "airportTo.name")

    })
    FlightDto mapToFlightDto(Flight flight);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "airportFrom", ignore = true),
            @Mapping(target = "airportTo", ignore = true),
            @Mapping(target = "tickets", ignore = true),
            @Mapping(target = "deleted",    ignore = true)})
    Flight mapToFlight(FlightRequest requestDto);
}
