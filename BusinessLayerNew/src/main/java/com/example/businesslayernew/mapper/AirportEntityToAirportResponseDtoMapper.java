package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.dto.response.AirportResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportEntityToAirportResponseDtoMapper {
//    TODO: рекомендую маппинги одной сущности ограничивать одним маппером с методами вида mapAirportDto, mapAirport
    AirportResponseDto map(AirportEntity airport);
}
