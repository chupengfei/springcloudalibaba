package com.qingkong.consumer.enums;

public enum  ResultEnum {

    SUCCESS(200,"成功"),
    SYS_ERROR(500,"系统级错误"),
    ILLEGAL_PARAM_ERROR(501,"非法参数错误"),
    PARAM_EMPTY_ERROR(502,"参数不能为空");

    ResultEnum( Integer status, String code){
        this.status = status;
        this.code = code;
    }
    private Integer status;
    private String code;

    public Integer getStatus() {
        return status;
    }
    public String getCode() {
        return code;
    }

}
