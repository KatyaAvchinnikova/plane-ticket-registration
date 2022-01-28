package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.Airport;
import com.example.businesslayernew.dto.airport.AirportDto;
import com.example.businesslayernew.dto.airport.AirportRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportDto mapAirportDto(Airport airport);

    Airport mapAirport(AirportRequest requestDto);

}
