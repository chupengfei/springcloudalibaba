package com.qingkong.product.service.impl;

import com.qingkong.product.common.RequestHolder;
import com.qingkong.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String hello() {
        log.info("current addr: {}, call method = hello",
                RequestHolder.getHttpServletRequest().getRequestURI());
        return "hello xiao fei ,nihao ya";
    }
}
