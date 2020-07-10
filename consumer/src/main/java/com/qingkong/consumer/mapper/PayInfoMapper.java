package com.qingkong.consumer.mapper;


import com.qingkong.consumer.entity.PayInfo;

/**
 * Created by smlz on 2019/11/20.
 */
public interface PayInfoMapper {

    PayInfo selectPayInfoByOrderNo(String orderNo);
}
