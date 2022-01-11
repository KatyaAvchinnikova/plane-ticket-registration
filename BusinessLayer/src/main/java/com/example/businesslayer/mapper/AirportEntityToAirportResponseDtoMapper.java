package com.example.businesslayer.mapper;

import com.example.businesslayer.domain.AirportEntity;
import com.example.businesslayer.dto.response.AirportResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportEntityToAirportResponseDtoMapper {
    AirportResponseDto map(AirportEntity airport);
}
