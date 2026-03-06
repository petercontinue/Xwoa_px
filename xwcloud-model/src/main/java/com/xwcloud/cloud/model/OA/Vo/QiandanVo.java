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
public class QiandanVo implements Serializable {
    private String kehucompanyname;
    private String khShowJigouName;
    private String kehucontractname;
    private String kehuothertel;
    private Long salestaffID;
    private String orderid;
    private BigDecimal ruanjianjine;
    private BigDecimal yingjianjine;
    private BigDecimal shishoumoney;
    private Date qiandanDatetime;
    private Integer campusNum;
    private Integer maxStuNum;
    private Long peixunTypeID;
    private Long taocanTypeID;
    private String hehuorenID;
    private String yxfromID;
    private String kehutelphone;
    private Date nextpayDate;
    private Long cityid;
    private Long provinceid;
    private String zengsong;
    private String buyhardwarelists;
    private String qiandanbeizhu;
    private Integer xinqianorxufei;
    private Integer hardwareFahuoState;
    private long qiyeID;
}
