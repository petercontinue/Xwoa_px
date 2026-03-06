package com.xwcloud.cloud.model.Vo;

import lombok.Data;

/**
 * 人工签到签退统计
 */
@Data
public class ExportQianCountVo {
    private String stuName;
    private String stuID;
    private int qdNum;
    private int qtNum;
}
