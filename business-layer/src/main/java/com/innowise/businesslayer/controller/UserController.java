package com.innowise.businesslayer.controller;

import com.innowise.businesslayer.domain.User;
import com.innowise.businesslayer.dto.user.UserDto;
import com.innowise.businesslayer.dto.user.UserRequest;
import com.innowise.businesslayer.feigns.ImageConsumer;
import com.innowise.businesslayer.mapper.UserMapper;
import com.innowise.businesslayer.service.MessagingService;
import com.innowise.businesslayer.service.UserService;
import com.innowise.message.FtpInfoMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
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
@Tag(name = "Users controller")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final MessagingService messagingService;
    private final ImageConsumer consumer;

    @PostMapping
    @Operation(summary = "Create new user")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserRequest request) {
        User user = userService.create(userMapper.mapToUser(request));
        messagingService.send(user);
        UserDto userDto = userMapper.mapToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Read all users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<UserDto>> readAll(@RequestParam(name = "isDeleted") boolean isDeleted,
            @ParameterObject Pageable pageable) {
        Page<UserDto> userDtoList = userService.getAll(isDeleted, pageable).map(userMapper::mapToUserDto);
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read user by id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDto> readById(@PathVariable Long id) {
        return new ResponseEntity<>(userMapper.mapToUserDto(userService.getById(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDto> update(@Valid @PathVariable Long id, @RequestBody UserRequest request) {
        UserDto userDto = userMapper.mapToUserDto(userService.update(id, userMapper.mapToUser(request)));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/files", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Store files")
    public ResponseEntity<String> store(@RequestParam("title") String title,
            @RequestPart("image") MultipartFile image) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
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

    @GetMapping(value = "/{id}/files")
    @Operation(summary = "Show files belong to user", security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<?> download(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return new ResponseEntity<>(consumer.download(id, email), HttpStatus.OK);
    }

    @GetMapping(value = "/file/", consumes = MediaType.ALL_VALUE,
                produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "Download image")
    public ResponseEntity<?> downloadImage(@RequestParam String id) {
        var image = consumer.downloadImage(id);
        return ResponseEntity.ok()
                             .contentType(MediaType.parseMediaType(image.getMimeType()))
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                                     + image.getTitle() + "\"")
                             .body(new ByteArrayResource(image.getImage()));
    }

}
