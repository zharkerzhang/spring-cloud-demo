package com.zharker.spring.cloud.gateway;

import com.netflix.zuul.ZuulFilter;
import com.zharker.spring.cloud.gateway.fallback.ProducerFallback;
import com.zharker.spring.cloud.gateway.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public ZuulFilter getTokenFilter(){
        return new TokenFilter();
    }

    @Bean
    public FallbackProvider getProducerFallback(){
        return new ProducerFallback();
    }
}
