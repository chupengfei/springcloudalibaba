package com.qingkong.consumer.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Slf4j
public class SpringInitialAB implements InitializingBean, SmartInitializingSingleton {

    public SpringInitialAB(){
        log.info("SpringInitialAB constructor instantiation ...");
    }

    @PostConstruct
    public void init(){
       log.info("SpringInitialAB init ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("SpringInitialAB afterPropertiesSet ... ");
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.info("SpringInitialAB afterSingletonsInstantiated ...");
    }
}
