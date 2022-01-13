package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.FlightEntity;
import com.example.businesslayernew.dto.response.FlightResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightEntityToFlightResponseDtoMapper {
    FlightResponseDto map(FlightEntity flight);
}
