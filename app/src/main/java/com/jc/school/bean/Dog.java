package com.jc.school.bean;

import io.realm.RealmObject;

/**
 * @author jiangchao
 * @Module.Name
 * @Create.Date 2018/6/17 上午10:45
 * @Update.By jiangchao
 * @Update.Content
 * @Update.Date 2018/6/17 上午10:45
 * @see
 */


public class Dog extends RealmObject {
    private String name;
    private int age;


    public Dog() {
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
