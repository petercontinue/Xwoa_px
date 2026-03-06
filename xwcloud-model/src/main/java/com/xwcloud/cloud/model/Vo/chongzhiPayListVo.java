package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class chongzhiPayListVo {
    private Long id;
    private Long stuID;
    private String chongzhiPayMoney;
    private String beizhu;
    private String type;
    private LocalDateTime addTime;
    private String stuName;
    private String staffName;
    private String campusName;
}
