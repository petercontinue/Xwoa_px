package com.xwcloud.cloud.model.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class chongzhiListVo {
    public  Long id;
    public Long stuID;
    private BigDecimal shijiChongzhiMoney;
    private BigDecimal songMoney;
    private BigDecimal shideTotalMoney;
    private String shuoming;
    private String staffName;
    private LocalDateTime chongzhiDatetime;
    private String campusName;
    private String stuName;
}
