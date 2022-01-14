package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.TicketEntity;
import com.example.businesslayernew.dto.request.TicketRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TicketDtoToTicketEntityMapper {
    @Mappings({
            @Mapping(target = "id",         ignore = true),
            @Mapping(target = "flight",     ignore = true),
            @Mapping(target = "user",       ignore = true)
    })
    TicketEntity map(TicketRequestDto ticketRequestDto);
}
