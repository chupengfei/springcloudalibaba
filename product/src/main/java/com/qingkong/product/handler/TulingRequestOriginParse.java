package com.qingkong.product.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @Auther: chupengfei_dxm
 * @Date: 2020/7/26 13:49
 */
@Component
@Slf4j
public class TulingRequestOriginParse implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        String token = request.getHeader("Token");
        if(StringUtils.isEmpty(token)) {
            log.warn("origin must not null");
            throw new IllegalArgumentException("request origin must not null");
        }
        return token;
    }
}
