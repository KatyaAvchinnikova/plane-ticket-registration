package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.dto.response.AirportResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportEntityToAirportResponseDtoMapper {
    AirportResponseDto map(AirportEntity airport);
}