package com.qingkong.consumer.controller;

import com.qingkong.consumer.common.RequestHolder;
import com.qingkong.consumer.common.Result;
import com.qingkong.consumer.enums.ResultEnum;
import com.qingkong.consumer.exception.MavenException;
import com.qingkong.consumer.listener.InitialCatch;
import com.qingkong.consumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping(value = "consumer")
@Slf4j
public class ConsumerController {

    @Autowired
    private InitialCatch initialCatch;
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("hello")
    public Object hello() {
        return consumerService.hello();
    }

    @RequestMapping("getInstance/{name}")
    public Object getInstance(@PathVariable(value = "name", required = true) String name) {
        log.info("ip地址:{}", RequestHolder.getHttpServletRequest().getRemoteHost());
        return consumerService.getServiceInstanceByServiceName(name);
    }

    @RequestMapping("getCatch")
    public Object getCatch() {
        log.info("catch :{}",initialCatch );
        return initialCatch;
    }

    @RequestMapping("exception")
    public Result exception(@Validated String name, @Validated int age) {
        if(StringUtils.equals(name,"xiaofei")){
            throw new MavenException("姓名不能输入小飞");
        }
        if(StringUtils.equals(name,"xueli")){
            throw new MavenException(ResultEnum.ILLEGAL_PARAM_ERROR);
        }
        return Result.success();
    }

    @RequestMapping("systemProperties")
    public Result systemProperties(){
        Map sysMap = new HashMap();
        Properties properties = System.getProperties();
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement().toString();
            sysMap.put(name, properties.getProperty(name));
        }
        return Result.success(sysMap);
    }


}
