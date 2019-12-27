package com.zharker.spring.cloud.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Value("${spring.profiles.active}")
    private String profile;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        boolean testRetry = Math.random() > 0.5;
        log.info("spring-cloud-producer handle /hello need test retry: {}",testRetry);
        if(testRetry){
            try {
                Thread.sleep(100000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello "+name + " from "+profile;
    }
}
