package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

//分配班主任
@Data
public class stuTearchVo {
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private String className;
    private String campusName;
    private String Banzhuren;
    private String yxfenpeistaffID;//分配人
    private Date yxfenpeiDate;//分配日期
}
