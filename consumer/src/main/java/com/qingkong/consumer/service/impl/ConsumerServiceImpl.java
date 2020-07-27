package com.qingkong.consumer.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qingkong.consumer.common.BlockUtils;
import com.qingkong.consumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
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

    @Override
    @SentinelResource(value = "sentinelService",
            blockHandler = "testSentinelBlockMethod",
            blockHandlerClass = BlockUtils.class)
   // @SentinelResource(value = "doSomeThing", blockHandler = "exceptionHandler")
    public String sentinelService() {
        return "正常返回";
    }

    // 限流与阻塞处理
    public String exceptionHandler(BlockException ex) {
        log.error( "我被限流了, blockHandler：", ex);
        return "failed";
    }
}
