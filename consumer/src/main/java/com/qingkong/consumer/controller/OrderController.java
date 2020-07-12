package com.qingkong.consumer.controller;

import com.qingkong.consumer.entity.OrderInfo;
import com.qingkong.consumer.entity.ProductInfo;
import com.qingkong.consumer.feignapi.ProductServiceApi;
import com.qingkong.consumer.mapper.OrderInfoMapper;
import com.qingkong.consumer.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    ProductServiceApi productServiceApi;

    @RequestMapping("/selectOrderInfoById/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo) {

        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        if(null == orderInfo) {
            return "根据orderNo:"+orderNo+"查询没有该订单";
        }

//        ResponseEntity<ProductInfo> responseEntity= restTemplate.getForEntity("http://service-provider/selectProductInfoById/"+orderInfo.getProductNo(), ProductInfo.class);
//
//        ProductInfo productInfo = responseEntity.getBody();
        // 使用feign掉用
        ProductInfo productInfo =  productServiceApi.selectProductInfoById(orderNo);

        if(productInfo == null) {
            return "没有对应的商品";
        }

        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(orderInfo.getOrderNo());
        orderVo.setUserName(orderInfo.getUserName());
        orderVo.setProductName(productInfo.getProductName());
        orderVo.setProductNum(orderInfo.getProductCount());

        return orderVo;
    }
}
