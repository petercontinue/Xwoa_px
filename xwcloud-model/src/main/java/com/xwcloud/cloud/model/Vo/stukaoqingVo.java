package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
/**
 * 转换学员考勤信息
 */
public class stukaoqingVo {
    private Long stuID;//学员ID
    private String kaoqing;//考勤
    private BigDecimal keshi;//要扣的课时
}
