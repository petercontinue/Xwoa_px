package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
public class shengrizhushouVO {
    private long dataID;
    private String name;
    private String touxiangImg;
    private String roleName;
    private Date birthday;
    private int dianzangCount;
    private int pinglunCount;
}
