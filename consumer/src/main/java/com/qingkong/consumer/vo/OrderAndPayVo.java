package com.qingkong.consumer.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by chupengfei on 2019/11/20.
 */
@Data
public class OrderAndPayVo {

    private String orderNo;

    private String userName;

    private String productNo;

    private Integer productCount;

    private String payNo;

    private Date payTime;
}
