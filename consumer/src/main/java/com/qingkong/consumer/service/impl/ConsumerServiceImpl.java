package com.qingkong.consumer.service.impl;

import com.qingkong.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public String hello() {
        String object = restTemplate.getForObject("http://service-provider/product/hello",
                String.class);
        return "callService: "+object;
    }

    @Override
    public List<ServiceInstance> getServiceInstanceByServiceName(String name) {
        return discoveryClient.getInstances(name);
    }
}
