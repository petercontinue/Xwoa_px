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
public class DaijinquanVo implements Serializable {

    private Long id;
    private String kehucompanyname;
    private BigDecimal djqRemain;
    private Long qiyeID;
    private BigDecimal getDjq;
    private BigDecimal useDjq;
    private String shuoming;
    private Long addUser;
    private String staffName;
    private Date addTime;


}
