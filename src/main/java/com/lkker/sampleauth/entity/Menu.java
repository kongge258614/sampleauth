package com.lkker.sampleauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Menu extends BaseOperation{

    @Id
    @Column(nullable = false, length = 32)
    private String id;


    /**
     * 连接地址
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 菜单类型
     */
    @Column(length = 12)
    private String menuType;

    /**
     * 排序
     */
    private Integer location;

    /**
     * 是否开放访问
     */
    private Boolean isPublic;



    @Column(length = 64)
    private String icon;

    /**
     * 子级
     */
    @OneToMany(mappedBy = "parentMenu")
    @Where(clause = "status!=-999")
    @JsonIgnoreProperties("parentMenu")
    @JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
    private List<Menu> subMenus;

    /**
     * 父级
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties("subMenus")
    private Menu parentMenu;


}
