package com.qingkong.product.controller;

import com.qingkong.product.entity.ProductInfo;
import com.qingkong.product.mapper.ProductInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/11/17.
 */
@RestController
@Slf4j
public class ProductInfoController {


    @Autowired
    private ProductInfoMapper productInfoMapper;

    @RequestMapping("/selectProductInfoById/{productNo}")
    public Object selectProductInfoById(@PathVariable("productNo") String productNo) {
        log.warn("我被掉用............");
        ProductInfo productInfo = productInfoMapper.selectProductInfoById(productNo);
        return productInfo;
    }
}
