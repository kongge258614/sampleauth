package com.lkker.sampleauth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @Author liliang
 * @Date 2020/6/28 13:58
 * @Description:用户表
 **/
@Entity(name = "authinfo")
@Data
public class AuthInfo {

    @Id
    private String id;

    private String password;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    @ManyToMany(mappedBy = "authInfos")
    private List<AuthRole> authRoles;
}
