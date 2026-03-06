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
public class YingjianInfoVo implements Serializable {

    private Long id;
    private String yingjianTypeName;
    private String kehucompanyname;
    private Long qiandanID;
    private Long qiyeID;
    private BigDecimal buyNum;
    private BigDecimal price;
    private BigDecimal totalMoney;
    private Integer xiadanState;
    private Long yingjianLiushuiID;
    private Date yingjianBuyTime;
    private String staffName;
    private String taobaoID;
    private Date addTime;
    private String shuoming;
    private String buyUser;

    private Long yingjianID;
    private Long yingjianBuyUser;

}
