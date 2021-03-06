package com.qingkong.consumer.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by chupengfei on 2019/11/17.
 */
@Data
public class OrderInfo {

    private String orderNo;

    private String userName;

    private Date createDt;

    private String productNo;

    private Integer productCount;

}
