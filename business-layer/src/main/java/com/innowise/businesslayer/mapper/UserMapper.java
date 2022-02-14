package com.innowise.businesslayer.mapper;

import com.innowise.businesslayer.domain.User;
import com.innowise.businesslayer.dto.user.UserDto;
import com.innowise.businesslayer.dto.user.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mappings({
//            @Mapping(target = "id",         ignore = true),
//            @Mapping(target = "tickets",    ignore = true),
//            @Mapping(target = "deleted",    ignore = true),
//            @Mapping(target = "role",       ignore = true)
//    })
    User mapToUser(UserRequest requestDto);

    UserDto mapToUserDto(User user);
}
