package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HehuorenVo implements Serializable {

    private Long id;
    private String realName;
    private String sex;
    private String phone;
    private BigDecimal fanyongbiFloat;
    private String shuoming;
    private Date levelStartTime;
    private Date levelEndTime;
    private Date firstQiandanTime;
    private String hehuoType;
    private Integer hhFrom;
    private Integer hhrRole;
    private String kehucompanyname;
    private String hehuoLevel;
    private String staffName;
    private Date addTime;
    private BigDecimal fangyongbili;
    private BigDecimal zongfyb;

    private String prinvince;
    private String city;
    private Long prinvinceID;
    private Long cityID;
    private Long hehuoLevelID;


}
