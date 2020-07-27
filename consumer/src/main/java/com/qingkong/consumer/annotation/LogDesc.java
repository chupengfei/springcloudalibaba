package com.qingkong.consumer.annotation;


import com.alibaba.csp.sentinel.EntryType;
import com.qingkong.consumer.enums.ResultEnum;

import java.lang.annotation.*;

/**
 * The annotation indicates a definition of Sentinel resource.
 *
 * @author Eric Zhao
 * @since 0.1.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDesc {

    String value() default "";

    ResultEnum resultEnum() default ResultEnum.SUCCESS;

    String desc() default "";
}

