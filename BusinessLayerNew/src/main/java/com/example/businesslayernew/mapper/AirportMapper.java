package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.Airport;
import com.example.businesslayernew.dto.airport.AirportDto;
import com.example.businesslayernew.dto.airport.AirportRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AirportMapper {
//    TODO: рекомендую маппинги одной сущности ограничивать одним маппером с методами вида mapAirportDto, mapAirport
    AirportDto mapAirportDto(Airport airport);

    @Mappings({
//            TODO: разве игнор необходим в таких кейсах?
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "flightsFrom",    ignore = true),
            @Mapping(target = "flightsTo",      ignore = true),
            @Mapping(target = "deleted",    ignore = true)})
    Airport mapAirport(AirportRequest requestDto);

}
