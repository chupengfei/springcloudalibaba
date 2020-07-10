package com.qingkong.consumer.config;

import com.netflix.loadbalancer.IRule;
import com.qingkong.consumer.rule.TheSameClusterPriorityRule;
import com.qingkong.consumer.rule.TheSameClusterPriorityWithVersionRule;
import com.qingkong.consumer.rule.WeightedRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/11/20.
 */
@Configuration
public class GlobalRibbonConfig {

    @Bean
    public IRule rule() {
        // return new WeightedRule();
        // return new TheSameClusterPriorityRule();
        return new TheSameClusterPriorityWithVersionRule();
    }
}
