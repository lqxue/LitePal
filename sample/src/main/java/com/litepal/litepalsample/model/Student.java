package com.litepal.litepalsample.model;

import com.litepal.crud.LitePalSupport;

public class Student extends LitePalSupport {
    private String age;


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
