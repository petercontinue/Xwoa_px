package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.List;

@Data
public class StaffVO {
    private long id;
    private String staffName;
    private String staffTel;
    private String staffSex;
    private long staffPostID;
    private String name;
    private String photo;
    private List<String> permission;
}
