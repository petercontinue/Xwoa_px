package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 自由签到班级已签到签退人数详情
 */
public class ziyouqiandaostuNumVo {
//    private String classID;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private int nums;//
//    private String qdNum;//签到人数
//    private String qtNum;//签退人数
}
