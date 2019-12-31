package com.zharker.spring.cloud.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

    @Value("${route.baidu.id}")
    private String routeBaiduId;
    @Value("${route.baidu.uri}")
    private String routeBaiduUri;
    @Value("${route.baidu.path}")
    private String routeBaiduPath;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(routeBaiduId, r -> r.path(routeBaiduPath).uri(routeBaiduUri))
                .build();
    }
}
