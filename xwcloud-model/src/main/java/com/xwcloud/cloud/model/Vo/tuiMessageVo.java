package com.xwcloud.cloud.model.Vo;

import lombok.Data;

//退费的信息
@Data
public class tuiMessageVo {
    private String stuID;
    private String payStyleID;
    private String yejirenID;
    private String processingTime;
    private String qiandanID;
    private String shuoming;
}
