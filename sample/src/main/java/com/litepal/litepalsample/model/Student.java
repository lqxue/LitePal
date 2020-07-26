package com.litepal.litepalsample.model;import com.litepal.crud.LitePalSupport;public class Student extends LitePalSupport {private String name;
    private String age;public String getName() {
        return name;
    }public void setName(String name) {
        this.name = name;
    }public String getAge() {
        return age;
    }public void setAge(String age) {
        this.age = age;
    }
}
