package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.dto.flight.FlightRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FlightRequestDtoToFlightEntityMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "airportFrom", ignore = true),
            @Mapping(target = "airportTo", ignore = true),
            @Mapping(target = "tickets", ignore = true)})
    FlightEntity map(FlightRequest requestDto);
}
