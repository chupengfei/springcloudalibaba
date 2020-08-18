package com.qingkong.consumer.config.configproperties;

import com.qingkong.consumer.entity.EntityPerson;
import com.qingkong.consumer.entity.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "maven.q.person")
@Data
public class TestPersonProperties {

    private String name;
    private String text;
    private EntityPerson person;
    private List<String> hobby;
    private List<EntityPerson> entityPeople;
    private Map<String, Student> studentMap = new HashMap<>();

    public TestPersonProperties(){}

    public TestPersonProperties(String text){
        this.text = text;
    }


}
