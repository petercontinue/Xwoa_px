package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
//宿舍管理
public class RoomManaVo {
    private String id;
    private String campusName;
    private int Num;//最大人数
    private int nowNum;
    private String RoomName;//宿舍号
}
