package com.qingkong.consumer.vo;

public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getStatus();

    /**
     * 返回mesg
     *
     * @return
     */
    String getMsg();
}
