package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.dto.flight.FlightRequest;
import com.example.businesslayernew.dto.flight.FlightResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface FlightMapper {
    @Mappings({
            @Mapping(target = "airportFrom", source = "airportFrom.name"),
            @Mapping(target = "airportTo", source = "airportTo.name")

    })
    FlightResponse mapToFlightDto(FlightEntity flight);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "airportFrom", ignore = true),
            @Mapping(target = "airportTo", ignore = true),
            @Mapping(target = "tickets", ignore = true)})
    FlightEntity mapToFlight(FlightRequest requestDto);
}
