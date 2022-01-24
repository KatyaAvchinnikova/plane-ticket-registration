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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping("/api/users")
@Api("Users controller")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    //    TODO: допустимо ли создание без регистрации?
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return userMapper.mapToUserDto(userService.create(userMapper.mapToUser(request)));
    }

    //    TODO: здесь и в остальных контроллерах: корректно ли дергать readAll без пагинации? Как насчет 1_000_000 записей в таблице?
    @GetMapping(params = {"page", "size", "isDeleted"})
    @ApiOperation("Read all users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> readAll(@RequestParam(name = "isDeleted") boolean isDeleted,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        return userService.getAll(isDeleted, page, size).stream().map((userMapper::mapToUserDto)).collect(
                Collectors.toList());
    }

    @GetMapping("*/{id}")
    @ApiOperation("Read user by id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> readById(@PathVariable Long id) {
        return new ResponseEntity<>(userMapper.mapToUserDto(userService.getById(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse update(@Valid @PathVariable Long id, @RequestBody UserRequest request) {
        return userMapper.mapToUserDto(userService.update(id,
                userMapper.mapToUser(request)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete user")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
