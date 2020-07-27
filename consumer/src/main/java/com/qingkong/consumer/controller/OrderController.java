package com.qingkong.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.qingkong.consumer.common.BlockUtils;
import com.qingkong.consumer.entity.OrderInfo;
import com.qingkong.consumer.entity.ProductInfo;
import com.qingkong.consumer.feignapi.ProductServiceApi;
import com.qingkong.consumer.mapper.OrderInfoMapper;
import com.qingkong.consumer.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RefreshScope
public class OrderController {

    @Value("${nacos.name}")
    private String name;
    @Value("${nacos.age}")
    private String age;
    @Value("${common.name}")
    private String commonName;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    ProductServiceApi productServiceApi;

    @RequestMapping("/selectOrderInfoById/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo,
                                      HttpServletRequest request) {

        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        if(null == orderInfo) {
            return "根据orderNo:"+orderNo+"查询没有该订单";
        }
        log.error("name = {}, age = {}, commonName={}", name, age, commonName);
//        ResponseEntity<ProductInfo> responseEntity= restTemplate.getForEntity("http://service-provider/selectProductInfoById/"+orderInfo.getProductNo(), ProductInfo.class);
//
//        ProductInfo productInfo = responseEntity.getBody();
        // 使用feign掉用
        ProductInfo productInfo = productServiceApi.selectProductInfoById(orderNo);

        if (productInfo == null) {
            return "没有对应的商品";
        }

        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(orderInfo.getOrderNo() + "-" + age);
        orderVo.setUserName(orderInfo.getUserName() + "-" + name);
        orderVo.setProductName(productInfo.getProductName() + "-" + commonName);
        orderVo.setProductNum(orderInfo.getProductCount());

        return orderVo;
    }
}
