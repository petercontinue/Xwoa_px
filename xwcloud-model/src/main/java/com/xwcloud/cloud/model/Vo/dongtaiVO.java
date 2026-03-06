package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
public class dongtaiVO {
    private long id;
    private long stuID;
    private String nickName;
    private String headImage;
    private String dongtaiContent;
    private String Addtime;
    private int iszhiding;
    private int yueduTimes;
    private int dianzanCount;
    private int pinglunCount;
    private int dongtaiCount;
}
