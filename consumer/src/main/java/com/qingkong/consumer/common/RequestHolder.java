package com.qingkong.consumer.common;
import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest持有者
 *
 * @Auther: chupengfei_dxm
 * @Date: 2020/7/4 18:13
 */
public class RequestHolder {

    public static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void setHttpServletRequest(HttpServletRequest request){
        requestHolder.set(request);
    }

    public static void removeServletRequest(){
        requestHolder.remove();
    }

    public static HttpServletRequest getHttpServletRequest(){
        return requestHolder.get();
    }
}
