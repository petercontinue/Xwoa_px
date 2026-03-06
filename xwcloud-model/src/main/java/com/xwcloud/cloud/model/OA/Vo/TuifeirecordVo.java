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
public class TuifeirecordVo implements Serializable {

    private Long id;
    private Date operatetuifeiDatetime;
    private String kehucompanyname;
    private Long qiandanID;
    private String staffName;
    private Date tuifeiDate;
    private BigDecimal tuifeiMoney;
    private String tuifeiReason;

}
