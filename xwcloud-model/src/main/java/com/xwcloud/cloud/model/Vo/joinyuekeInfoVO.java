package com.xwcloud.cloud.model.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class joinyuekeInfoVO {
    private Long id;
    private Long wxUserID;
    private Long stuID;
    private String stuName;
    private String telphone;
    private Long buxiID;
    private Long yuekeTeachFabuID;
    private String addTime;
    private String beizhu;
    private Integer paystate;
    private BigDecimal paymoney;
    private String paytime;
    private Integer qiyeID;
}
