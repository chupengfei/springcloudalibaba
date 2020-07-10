package com.qingkong.consumer.mapper;


import com.qingkong.consumer.entity.OrderInfo;

/**
 * Created by smlz on 2019/11/17.
 */
public interface OrderInfoMapper {

    OrderInfo selectOrderInfoById(String orderNo);
}
