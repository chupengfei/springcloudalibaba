package com.qingkong.product.mapper;


import com.qingkong.product.entity.ProductInfo;

/**
 * Created by chupengfei on 2019/11/17.
 */
public interface ProductInfoMapper {

    ProductInfo selectProductInfoById(String productNo);
}
