package com.lkker.sampleauth.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author liliang
 * @Date 2020/6/28 14:19
 * @Description: 角色
 **/
@Data
@Entity(name = "authrole")
public class AuthRole {
    @Id
    private String id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "authRoles")
    private List<AuthPermission> authPermissions;

    @ManyToMany
    @JoinTable(name = "authinfo_authrole",
    joinColumns = {@JoinColumn(name = "authrole_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authinfo_id",referencedColumnName = "id")})
    private List<AuthInfo> authInfos;

}
