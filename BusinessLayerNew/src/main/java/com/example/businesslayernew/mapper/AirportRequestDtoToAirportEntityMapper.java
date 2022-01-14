package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.dto.request.AirportRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AirportRequestDtoToAirportEntityMapper {

    @Mappings({
            @Mapping(target = "id",             ignore = true),
            @Mapping(target = "flightsFrom",    ignore = true),
            @Mapping(target = "flightsTo",      ignore = true)})
    AirportEntity map(AirportRequestDto requestDto);

}
