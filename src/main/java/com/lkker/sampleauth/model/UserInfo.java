package com.lkker.sampleauth.model;

/**
 * @Author liliang
 * @Date 2020/7/4 17:35
 * @Description
 **/

public class UserInfo {

    private String id;

    private String name;

    private String mobile;

    public UserInfo(String id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
