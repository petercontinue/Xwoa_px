package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
//发证
public class fazhengVo {
    private String id;
    private String stuID;
    private String stuName;
    private String kechengName;
    private String FZImage;
    private String zsName;
    private String ZSid;
    private int FZstate;
    private String FZstaff;
    private Date FZdate;
}
