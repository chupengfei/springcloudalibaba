package com.qingkong.consumer.common;

import com.qingkong.consumer.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer status;
    private String msg;
    private T data;

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    public static Result failed() {
        return new Result(0, "failed", null);
    }

    public static Result result(ResultEnum resultEnum){
        return result(resultEnum.getStatus(),resultEnum.getCode(), null);
    }

    public static Result result(Integer status, String msg, Object data){
        return new Result(status, msg, data);
    }

}
