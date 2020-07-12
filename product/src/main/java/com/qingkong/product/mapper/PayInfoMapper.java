package com.qingkong.product.mapper;


import com.qingkong.product.entity.PayInfo;

/**
 * Created by chupengfei on 2019/11/20.
 */
public interface PayInfoMapper {

    PayInfo selectPayInfoByOrderNo(String orderNo);
}
