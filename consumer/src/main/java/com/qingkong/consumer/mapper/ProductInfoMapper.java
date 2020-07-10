package com.qingkong.consumer.mapper;


import com.qingkong.consumer.entity.ProductInfo;

/**
 * Created by smlz on 2019/11/17.
 */
public interface ProductInfoMapper {

    ProductInfo selectProductInfoById(String productNo);
}
