package com.qingkong.consumer.listener;

import com.qingkong.consumer.entity.Student;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InitialCatch implements ApplicationListener<ApplicationEvent> {

    private  Map<String, Object> map = new ConcurrentHashMap<>();
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        map.put("xiaofei",new Student("xiaofei",11));
        map.put("ruona",new Student("ruona",12));
    }

    public Map<String, Object> getCatch(){
        return map;
    }
}
