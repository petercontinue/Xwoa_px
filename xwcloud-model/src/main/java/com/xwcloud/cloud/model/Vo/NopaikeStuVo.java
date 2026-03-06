package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
/**
 * 保存排课学员数据
 */
public class NopaikeStuVo {
    private Long stuID;//学员ID
    private String kaoqing;//考勤
    private BigDecimal keshi;//要扣的课时
    private String buxiID;//补习ID 手动添加进排课的学生才有补习ID，，其他默认为0
}
