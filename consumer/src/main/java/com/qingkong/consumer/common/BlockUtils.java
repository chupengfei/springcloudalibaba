package com.qingkong.consumer.common;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by smlz on 2020/2/13.
 */
@Slf4j
public class BlockUtils {


    public static String testSentinelBlockMethod(BlockException e){
        log.info("sentinelService方法被流控了");
        return "sentinelService方法被流控了";
    }

    public static String testSentinelBlockMethod1(BlockException e){
        log.info("getCatch1方法被流控了");
        return "getCatch1方法被流控了";
    }


}
