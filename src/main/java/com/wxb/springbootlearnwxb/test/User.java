package com.wxb.springbootlearnwxb.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wangxianbing
 * @date 2021-08-02 09:48:09
 */

public class User implements Serializable {
    private Integer age;
    private String name;
    private java.lang.String formInstanceId;

    public String getFormInstanceId() {
        return formInstanceId;
    }

    public void setFormInstanceId(String formInstanceId) {
        this.formInstanceId = formInstanceId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
