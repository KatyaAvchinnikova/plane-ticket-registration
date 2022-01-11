package com.example.businesslayer.mapper;

import com.example.businesslayer.domain.AirportEntity;
import com.example.businesslayer.dto.request.AirportRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AirportRequestDtoToAirportEntityMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "flightsFrom", ignore = true),
            @Mapping(target = "flightsTo", ignore = true)})
    AirportEntity map(AirportRequestDto requestDto);

}
