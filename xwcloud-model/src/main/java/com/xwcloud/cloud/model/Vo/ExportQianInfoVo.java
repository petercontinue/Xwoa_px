package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
/**
 * 人工签到签退详情
 */
public class ExportQianInfoVo {
    private String stuName;
    private String DorT;//签到/签退 1签到 2签退
    private String qianStyle;//签到签退方式 1刷卡 2微信 3 人工
    private String haveClassDate;//(拼接好的时间)
    private String tsState;//是否推送成功
    private Date qianDatetime;//签到时间
    private String addStaffName;//添加人
}
