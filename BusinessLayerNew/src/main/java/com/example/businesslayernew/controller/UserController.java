package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.user.UserRequest;
import com.example.businesslayernew.dto.user.UserResponse;
import com.example.businesslayernew.mapper.UserMapper;
import com.example.businesslayernew.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping(value = "/api/users")
@Api("Users controller")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

//    TODO: допустимо ли создание без регистрации?
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new user")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){

        UserResponse userResponseDto =
                userMapper.mapToUserDto(userService.create(userMapper.mapToUser(request)));

        return new ResponseEntity<>( userResponseDto, HttpStatus.CREATED);

    }

//    TODO: здесь и в остальных контроллерах: корректно ли дергать readAll без пагинации? Как насчет 1_000_000 записей в таблице?
    @GetMapping
    @ApiOperation("Read all users")
    public List<UserResponse> readAll(@PathVariable("isDeleted") Boolean isDeleted){

        return userService.getAll(isDeleted).stream().map((userMapper::mapToUserDto)).collect(
                Collectors.toList());

    }

    @GetMapping("*/{id}")
    @ApiOperation("Read user by id")
    public ResponseEntity<UserResponse> readById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userMapper.mapToUserDto(userService.getById(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update user")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @RequestBody UserRequest request){
        UserResponse userResponseDto = userMapper.mapToUserDto(userService.update(id,
                userMapper.mapToUser(request)));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete user")
    public ResponseEntity<UserResponse> delete(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
