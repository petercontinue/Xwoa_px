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
public class QiandanXufeiVo implements Serializable {

    private Long id;
    private Integer addCampusNum;
    private String buyhardwarelists;
    private BigDecimal daijinquan;
    private Long newtaocanID;
    private Date nextpayDate;
    private String orderid;
    private Date qiandanDatetime;
    private String addCampusNextpayDate;
    private String qiandanbeizhu;
    private Long qiyeID;
    private BigDecimal ruanjianjine;
    private Long salestaffID;
    private BigDecimal shishoumoney;
    private Long taocanID;
//    private Integer xufeiType;
    private BigDecimal yingjianjine;
    private String zengsong;
    private int maxStuNum;
    private int hardwareFahuoState;//硬件？

//    private Long paymoneystyleID;
//    private Long productID;
}
