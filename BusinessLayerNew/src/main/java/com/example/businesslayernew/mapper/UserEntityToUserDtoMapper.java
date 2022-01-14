package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.UserEntity;
import com.example.businesslayernew.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserEntityToUserDtoMapper {
    UserResponseDto map(UserEntity user);
}
