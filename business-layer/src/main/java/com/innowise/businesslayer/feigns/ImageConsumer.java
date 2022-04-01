package com.innowise.businesslayer.feigns;

import com.innowise.message.FtpInfoMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("business-layer")
public interface ImageConsumer {

    @GetMapping("/api/users/{id}/files")
    public List<FtpInfoMessage> download(@PathVariable Long id, String email);

}
