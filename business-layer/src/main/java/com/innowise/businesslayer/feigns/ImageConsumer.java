package com.innowise.businesslayer.feigns;

import com.innowise.message.FtpInfoMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("ftp-client")
public interface ImageConsumer {

    @GetMapping("/api/users/{id}/files")
    List<String> download(@PathVariable Long id, @RequestParam String email);

    @GetMapping("/api/users/file/")
    FtpInfoMessage downloadImage(@RequestParam String id);

}
