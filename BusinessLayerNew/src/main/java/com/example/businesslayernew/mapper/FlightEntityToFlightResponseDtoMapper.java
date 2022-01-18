package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.dto.flight.FlightResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface FlightEntityToFlightResponseDtoMapper {
    @Mappings({
            @Mapping(target = "airportFrom", source = "airportFrom.name"),
            @Mapping(target = "airportTo", source = "airportTo.name")
    })
    FlightResponse map(FlightEntity flight);
}
