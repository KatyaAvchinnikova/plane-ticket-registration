package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.UserEntity;
import com.example.businesslayernew.dto.request.UserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDtoToUserMapper {
    @Mappings({
            @Mapping(target = "id",         ignore = true),
            @Mapping(target = "tickets",    ignore = true),
            @Mapping(target = "deleted",    ignore = true),
            @Mapping(target = "role",       ignore = true)
    })
    UserEntity map(UserRequestDto requestDto);
}
