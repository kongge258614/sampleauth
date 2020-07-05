package com.lkker.sampleauth.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author liliang
 * @Date 2020/6/28 14:22
 * @Description
 **/

@Entity(name = "authpermission")
@Data
public class AuthPermission {


    @Id
    private String id;

    /**
     * 目标url
     */
    @Column(unique = true)
    private String url;

    /**
     * url说明
     */
    private String description;

    @ManyToMany
    @JoinTable(name = "authrole_authpermisson",
    joinColumns = {@JoinColumn(name = "authpermisson_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authrole_id",referencedColumnName = "id")}
    )
    private List<AuthRole> authRoles;


    /**
     * 是否是开放接口
     */
//    private Boolean isPublic;


    /**
     * 是否可以修改
     */
//    private Boolean editable;
}
