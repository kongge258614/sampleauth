package com.lkker.sampleauth.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public class BaseOperation implements Serializable {

    private long createTime;

    private long updateTime;

    @Column(length = 4)
    private Integer status;
}
