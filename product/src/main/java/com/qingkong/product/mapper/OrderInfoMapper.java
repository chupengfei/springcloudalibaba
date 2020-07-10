package com.qingkong.product.mapper;


import com.qingkong.product.entity.OrderInfo;

/**
 * Created by smlz on 2019/11/17.
 */
public interface OrderInfoMapper {

    OrderInfo selectOrderInfoById(String orderNo);
}
