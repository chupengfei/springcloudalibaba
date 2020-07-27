package com.qingkong.consumer.handler;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.qingkong.consumer.entity.ProductInfo;
import com.qingkong.consumer.feignapi.ProductServiceApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * feign中使用sentinel做fallback降级处理
 * @Auther: chupengfei_dxm
 * @Date: 2020/7/27 18:11
 */

//todo sentinel整合feign的熔断降级如何处理？

@Component
@Slf4j
public class ProductCenterFeignApiWithSentinelFallbackFactory implements FallbackFactory<ProductServiceApi> {
    @Override
    public ProductServiceApi create(Throwable throwable) {
        return new ProductServiceApi() {
            @Override
            public ProductInfo selectProductInfoById(String productNo) {
                ProductInfo productInfo = new ProductInfo();
                if (throwable instanceof FlowException) {
                    log.error("流控了....{}", throwable.getMessage());
                    productInfo.setProductName("我是被流控的默认商品");
                } else {
                    log.error("降级了....{}", throwable.getMessage());
                    productInfo.setProductName("我是被降级的默认商品");
                }
                return productInfo;
            }
        };
    }
}
