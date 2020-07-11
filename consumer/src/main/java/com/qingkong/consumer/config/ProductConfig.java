package com.qingkong.consumer.config;

import com.qingkong.consumer.listener.InitialCatch;
import com.qingkong.consumer.processor.SpringInitialAB;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductConfig {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public InitialCatch initialCatch(){
        return new InitialCatch();
    }

    @Bean
    public SpringInitialAB springInitialAB(){
        return new SpringInitialAB();
    }
}
