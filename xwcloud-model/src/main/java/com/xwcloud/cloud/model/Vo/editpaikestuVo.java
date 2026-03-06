package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 修改排课学员数据
 */
public class editpaikestuVo {
    private Boolean cked;
    private Long ID;
    private Long bxID;
    private String stuName;
    private String type;
    private String kcName;
    private int classtype;
}
