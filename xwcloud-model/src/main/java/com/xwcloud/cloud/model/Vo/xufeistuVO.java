package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
public class xufeistuVO {
    private long id;
    private String stuName;
    private long stuGradeID;//用于续费单价判断
    private long campusID;
    private String parentTel;
    private String parentTelRelation;
}
