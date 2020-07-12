package com.qingkong.consumer.feignapi;

import com.qingkong.consumer.config.feign.ProductCenterFeignConfig;
import com.qingkong.consumer.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-provider",
        configuration = ProductCenterFeignConfig.class)
public interface ProductServiceApi {

    @RequestMapping("/selectProductInfoById/{productNo}")
    ProductInfo selectProductInfoById(@PathVariable("productNo") String productNo);

}
