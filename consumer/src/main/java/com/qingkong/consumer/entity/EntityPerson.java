package com.qingkong.consumer.entity;

import lombok.Data;

@Data
public class EntityPerson {
    private String text;
    private int age;
    private String sys;

    public EntityPerson(){}

    public EntityPerson(String text){
        this.text =text;
    }

}
