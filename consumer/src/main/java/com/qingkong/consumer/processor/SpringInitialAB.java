package com.qingkong.consumer.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Slf4j
public class SpringInitialAB implements InitializingBean, SmartInitializingSingleton {

    public SpringInitialAB(){
        log.warn("SpringInitialAB constructor instantiation ...");
    }

    @PostConstruct
    public void init(){
       log.warn("SpringInitialAB init ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.warn("SpringInitialAB afterPropertiesSet ... ");
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.warn("SpringInitialAB afterSingletonsInstantiated ...");
    }
}
