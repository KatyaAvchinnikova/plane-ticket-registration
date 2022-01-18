package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.user.UserRequest;
import com.example.businesslayernew.dto.user.UserResponse;
import com.example.businesslayernew.mapper.UserDtoToUserMapper;
import com.example.businesslayernew.mapper.UserEntityToUserDtoMapper;
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
    private final UserDtoToUserMapper userDtoToUserMapper;
    private final UserEntityToUserDtoMapper userEntityToUserDtoMapper;

//    TODO: допустимо ли создание без регистрации?
    //create new user
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new user")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){

        UserResponse userResponseDto =
                userEntityToUserDtoMapper.map(userService.create(userDtoToUserMapper.map(request)));

        return new ResponseEntity<>( userResponseDto, HttpStatus.CREATED);

    }

//    TODO: здесь и в остальных контроллерах: корректно ли дергать readAll без пагинации? Как насчет 1_000_000 записей в таблице?
    //read all users
    @GetMapping
    @ApiOperation("read all users")
    public List<UserResponse> readAll(){

        return userService.readAll().stream().map((userEntityToUserDtoMapper::map)).collect(
                Collectors.toList());

    }

    //read by id
    @GetMapping("{id}")
    @ApiOperation("read user by id")
    public ResponseEntity<UserResponse> readById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userEntityToUserDtoMapper.map(userService.readById(id)), HttpStatus.OK);
    }

    //update user
    @PatchMapping("{id}")
    @ApiOperation("update user")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @RequestBody UserRequest request){
        UserResponse userResponseDto = userEntityToUserDtoMapper.map(userService.update(id,
                userDtoToUserMapper.map(request)));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    //delete user
    @DeleteMapping("{id}")
    @ApiOperation("delete user")
    public ResponseEntity<UserResponse> delete(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
