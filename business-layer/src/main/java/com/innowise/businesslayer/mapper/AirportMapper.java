package com.innowise.businesslayer.mapper;

import com.innowise.businesslayer.domain.Airport;
import com.innowise.businesslayer.dto.airport.AirportDto;
import com.innowise.businesslayer.dto.airport.AirportRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportDto mapAirportDto(Airport airport);

    Airport mapAirport(AirportRequest requestDto);
}
