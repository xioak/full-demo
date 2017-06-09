package com.xioa.model;

public class Pril {
    private Integer id;

    private String prilName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrilName() {
        return prilName;
    }

    public void setPrilName(String prilName) {
        this.prilName = prilName == null ? null : prilName.trim();
    }
}