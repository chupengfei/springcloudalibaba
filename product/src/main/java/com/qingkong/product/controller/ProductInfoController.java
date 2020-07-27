package com.qingkong.product.controller;

import com.qingkong.product.common.RequestHolder;
import com.qingkong.product.entity.ProductInfo;
import com.qingkong.product.mapper.ProductInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@RestController
@Slf4j
@RefreshScope
public class ProductInfoController {

    @Value("${nacos.name}")
    private String name;
    @Value("${nacos.age}")
    private String age;
    @Value("${common.name}")
    private String commonName;
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @RequestMapping("/selectProductInfoById/{productNo}")
    public ProductInfo selectProductInfoById(@PathVariable("productNo") String productNo) {
        HttpServletRequest httpServletRequest = RequestHolder.getHttpServletRequest();
        String token = httpServletRequest.getHeader("Token");
        if(Objects.equals(productNo, "2")){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(Objects.equals(productNo, "3")){
            throw new RuntimeException("cuowu");
        }
        log.warn("我被掉用............token = {}", token);
        ProductInfo productInfo = productInfoMapper.selectProductInfoById(productNo);
        return productInfo;
    }
}
