package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 不排课消课学员信息
 */
public class nopaikestuDateVo {
    private Boolean cked;
    private Long ID;
    private Long bxID;
    private String stuName;
    private String type;
    private String kcName;
}
