package com.qingkong.consumer.common;

import com.qingkong.consumer.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
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

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleWithFixedDelay(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println((int) (Math.random() * 5) + 1);
            log.warn(Thread.currentThread().getName()+": integer = "+ integer.incrementAndGet());
        },5,1, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName()+": integer = "+ integer.incrementAndGet());
        },5,1, TimeUnit.SECONDS);
    }

}
