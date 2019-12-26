package com.zharker.spring.cloud.producer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${spring.profiles}")
    private String profile;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name + " from "+profile;
    }
}
