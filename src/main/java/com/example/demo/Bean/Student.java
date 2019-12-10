package com.example.demo.Bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Student implements Serializable {
    private static final long serialVersionUID = -339516038496531943L;
    private String no;
    private String name;
    private String sex;
    private String password;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
