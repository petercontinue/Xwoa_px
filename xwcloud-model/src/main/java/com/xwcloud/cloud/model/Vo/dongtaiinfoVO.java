package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
public class dongtaiinfoVO {
    private long id;
    private String dongtaiTitle;
    private String dongtaiContent;
    private String addtime;
    private String nickName;
    private String headImage;
    private int dianzanCount;
    private int pinglunCount;
    private int dongtaiCount;
    private int guanzhu;
    private long wscuserID;
    private int yueduTimes;
}
