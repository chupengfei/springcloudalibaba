package com.qingkong.consumer.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by chupengfei on 2019/11/20.
 */
@Data
public class PayInfo {

    private String payNo;

    private String orderNo;

    private String userName;

    private Date payTime;
}
