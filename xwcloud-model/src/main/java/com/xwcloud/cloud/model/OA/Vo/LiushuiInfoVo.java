package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiushuiInfoVo implements Serializable {

    private Long id;
    private String staffName;
    private Long jinbanrenStaffID;
    private Date liushuiDatetime;
    private String liushuishuoming;
    private Date lurutime;
    private String paymoneyStyleName;
    private Long paymoneystyleID;
    private Long qiandanID;
    private BigDecimal shourumoney;
    private BigDecimal zhichumoney;
    private String liushuiStyle;
    private Long liushuiStyleID;
    private Integer isShouruOrZhichu;

}
