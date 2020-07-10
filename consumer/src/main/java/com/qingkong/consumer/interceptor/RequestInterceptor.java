package com.qingkong.consumer.interceptor;


import com.qingkong.consumer.common.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("RequestInterceptor -> preHandle(), requestHolder中的request: {}",
                RequestHolder.getHttpServletRequest());
        RequestHolder.setHttpServletRequest(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("RequestInterceptor -> postHandle(), requestHolder中的request: {}",
                RequestHolder.getHttpServletRequest());
        RequestHolder.removeServletRequest();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("RequestInterceptor -> afterCompletion(), requestHolder中的request: {}",
                RequestHolder.getHttpServletRequest());
    }
}
