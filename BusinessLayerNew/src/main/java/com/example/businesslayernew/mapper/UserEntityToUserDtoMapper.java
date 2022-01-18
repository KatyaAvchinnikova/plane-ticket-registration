package com.example.businesslayernew.mapper;

import com.example.businesslayernew.domain.UserEntity;
import com.example.businesslayernew.dto.user.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityToUserDtoMapper {
    UserResponse map(UserEntity user);
}
