package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class guigeshuxingVo {
    private String id;
    private String goodsid;
    private String goodsguigetypeid;
    private String shuxingming;
    private String shuxingpaixu;
    private String isneedchangimg;
    private String addstaffid;
    private LocalDateTime addtime;
    private String qiyeID;
    private Boolean check=false;
}
