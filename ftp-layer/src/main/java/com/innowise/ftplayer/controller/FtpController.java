package com.innowise.ftplayer.controller;

import com.innowise.ftplayer.dto.FtpRequest;
import com.innowise.ftplayer.service.FtpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ftp")
@Api("FTP controller")
public class FtpController {
    private final FtpService ftpService;

    @PostMapping
    @ApiOperation("Store file")
    public ResponseEntity<String> store(FtpRequest request) throws IOException {
        ftpService.storeImage(request);
        return new ResponseEntity<>("Your file is stored", HttpStatus.CREATED);
    }
}
