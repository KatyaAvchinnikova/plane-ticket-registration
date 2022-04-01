package com.innowise.businesslayer.feigns;

import com.innowise.message.FtpInfoMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("business")
public interface FeignService {

    @RequestMapping("/api/users/files")
    public List<FtpInfoMessage> getImages();

}
