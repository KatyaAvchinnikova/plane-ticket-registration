package com.innowise.businesslayer.controller;

import com.innowise.businesslayer.domain.User;
import com.innowise.businesslayer.dto.user.UserDto;
import com.innowise.businesslayer.dto.user.UserRequest;
import com.innowise.businesslayer.mapper.UserMapper;
import com.innowise.businesslayer.service.MessagingService;
import com.innowise.businesslayer.service.SecurityService;
import com.innowise.businesslayer.service.UserService;
import com.innowise.message.FtpInfoMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Api("Users controller")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final MessagingService messagingService;
    private final SecurityService securityService;

    @PostMapping
    @ApiOperation("Create new user")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserRequest request) {
        User user = userService.create(userMapper.mapToUser(request));
        messagingService.send(user);
        UserDto userDto = userMapper.mapToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("Read all users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<UserDto>> readAll(@RequestParam(name = "isDeleted") boolean isDeleted,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<UserDto> userDtoList = userService.getAll(isDeleted, pageable).map(userMapper::mapToUserDto);
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Read user by id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDto> readById(@PathVariable Long id) {
        return new ResponseEntity<>(userMapper.mapToUserDto(userService.getById(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDto> update(@Valid @PathVariable Long id, @RequestBody UserRequest request) {
        UserDto userDto = userMapper.mapToUserDto(userService.update(id, userMapper.mapToUser(request)));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation("Store files")
    public ResponseEntity<String> store(@RequestParam("title") String title,
            @RequestPart("image") MultipartFile image) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = userService.findByUserName(authentication.getName()).getEmail();
        FtpInfoMessage message = FtpInfoMessage.builder()
                                               .email(email)
                                               .image(new Binary(BsonBinarySubType.BINARY, image.getBytes()))
                                               .title(title)
                                               .build();
        if (Objects.equals(image.getContentType(), "image/png")) {
            message.setMimeType("image/x-png");
        } else {
            message.setMimeType(image.getContentType());
        }
        messagingService.store(message);
        return new ResponseEntity<>("Your file is stored successfully", HttpStatus.CREATED);
    }

}
