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
public class QiandanInfoVo implements Serializable {

    private Long id;
    private Long area;
    private String areaname;
    private String productName;
    private String buyhardwarelists;
    private int hardwareFahuoState;
    private String realName;
    private BigDecimal fanyong;
    private int fanyongState;
    private String staffName;
    private Date fanyongTime;
    private Long fanyongLiushuiID;
    private String fanyongBeizhu;
    private String hetong;
    private String kehucompanyname;
    private String khShowJigouName;
    private String orderid;
    private int kehuType;
    private Date qiandanDatetime;
    private int qiandanstate;
    private String qiandanbeizhu;
    private BigDecimal ruanjianjine;
    private BigDecimal shishoumoney;
    private String taocanName;
    private int xinqianorxufei;
    private BigDecimal yingjianjine;
    private String zengsong;

    private String province;
    private String city;

    private Long taocanID;
    private String addStaffID;
    private Long productID;
    private Long salestaffID;

}
