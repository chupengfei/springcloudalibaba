package com.qingkong.consumer.service;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface ConsumerService {

    String hello();

    List<ServiceInstance> getServiceInstanceByServiceName(String name);

    String sentinelService();
}
