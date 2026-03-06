package com.xwcloud.cloud.model.Vo;

import lombok.Data;

//导出学员住宿
@Data
public class ExportStuRoomVo {
    private String campusName;
    private String stuID;
    private String StuName;
    private String banzhuren;
    private String RoomNum;
    private String stuGradeName;
}
