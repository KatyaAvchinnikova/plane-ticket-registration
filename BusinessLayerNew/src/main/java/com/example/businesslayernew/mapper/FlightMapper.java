package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.Flight;
import com.example.businesslayernew.dto.flight.FlightRequest;
import com.example.businesslayernew.dto.flight.FlightDto;
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
            @Mapping(target = "tickets", ignore = true)})
    Flight mapToFlight(FlightRequest requestDto);
}
