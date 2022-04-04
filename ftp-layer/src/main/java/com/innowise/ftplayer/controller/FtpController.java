package com.innowise.ftplayer.controller;

import com.innowise.ftplayer.service.FtpService;
import com.innowise.message.FtpInfoMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Api("FTP controller")
public class FtpController {

    private final FtpService ftpService;

    @GetMapping("/{id}/files")
    @ApiOperation("Show list of images belong to user")
    public List<String> download(@PathVariable Long id, @RequestParam String email) {
        return ftpService.download(email);
    }

    @GetMapping("/file/")
    @ApiOperation("Download image")
    public FtpInfoMessage getPhoto(@RequestParam String id){
        return ftpService.getPhoto(id);
    }

}
