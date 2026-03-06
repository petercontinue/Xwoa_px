package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
public class stuqiaoDaoNumVo {
    private String qType;//签到类型
    private Date qianDatetime;
    private String TStype;//是否推送成功
    private String staffName;//添加人
}
