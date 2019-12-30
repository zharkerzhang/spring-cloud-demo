package com.zharker.spring.cloud.consul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceController {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${consul.producer}")
    private String consulProducer;

    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances(consulProducer);
    }

    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancer.choose(consulProducer).getUri().toString();
    }

    @RequestMapping("/call")
    public String call() {
        ServiceInstance serviceInstance = loadBalancer.choose(consulProducer);
        String producerUri = serviceInstance.getUri().toString();
        return new RestTemplate().getForObject(producerUri+"/hello",String.class);
    }
}
