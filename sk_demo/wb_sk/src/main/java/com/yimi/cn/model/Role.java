package com.yimi.cn.model;

import java.io.Serializable;

public class Role implements Serializable {
    /**
     * id
     * 
     */
    private Integer id;

    /**
     * role_name
     * 
     */
    private String roleName;

    /**
     * role
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}