package com.qingkong.consumer.exception;

import com.qingkong.consumer.enums.ResultEnum;

public class MavenException extends RuntimeException {
    private ResultEnum resultEnum;

    public MavenException(String msg) {
        super(msg);
    }

    public MavenException(ResultEnum resultEnum) {
        super(resultEnum.getCode());
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
}
