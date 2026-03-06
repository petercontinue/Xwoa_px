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
public class YiqiandanKehuVo implements Serializable {

    private Long id;
    private Long qiyeID;
    private String kehucompanyname;
    private int qiandanstate;
    private int kehuType;
    private Integer kehuUseState;
    private String manyiduName;
    private String kehucontractname;
    private String kehutelphone;
    private String hangyetypename;
    private String pxcontent;
    private Date firstqiandandatetime;
    private String hetong;
    private Date nextpaydatetime;
    private String staffName;
    private BigDecimal djqRemain;
    private String areaname;
    private String afterstaffName;

    private Integer peixunCount;
    private String color;

    private String province;
    private String city;


}
