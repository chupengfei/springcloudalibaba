package com.qingkong.consumer.config.feign;

import com.qingkong.consumer.interceptor.FeignRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 这个类上千万不要添加@Configuration,不然会被作为全局配置文件共享
 * Created by chupengfei on 2019/11/22.
 */
public class ProductCenterFeignConfig {

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
        //return Logger.Level.HEADERS;
        // return Logger.Level.BASIC;
        //return Logger.Level.NONE;
    }

    /**
     * 根据SpringBoot自动装配FeignClientsConfiguration 的FeignClient的契约是SpringMvc
     *
     通过修改契约为默认的Feign的锲约，那么就可以使用默认的注解
     * @return
     */

/*    @Bean
    public Contract feiContract() {
        return new Contract.Default();
    }*/

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new FeignRequestInterceptor();
//    }
    @Bean
    public RequestInterceptor headerInterceptor() {
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        template.header(name, values);
                    }
                }
            }
        };
    }

}
