package com.innowise.ftplayer.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public interface DiscoveryController {
    @RequestMapping("/greeting")
    String greeting();

}
