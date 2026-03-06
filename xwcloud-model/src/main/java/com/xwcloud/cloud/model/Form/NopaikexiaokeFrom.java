package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class NopaikexiaokeFrom {
    @ApiModelProperty(value = "日期", required = true)
    private String allriqi;

    @ApiModelProperty(value = "不排课消课，计入教师课时数", required = true)
    private BigDecimal teacherkeshiNum;

    @ApiModelProperty(value = "学员消课数据", required = true)
    private String newstu;

    @ApiModelProperty(value = "课程", required = true)
    private Long kechengID;

    @ApiModelProperty(value = "选中的班级", required = true)
    private Long classidStr;

    @ApiModelProperty(value = "选中的上课教师", required = true)
    private String shangketeacher;

    @ApiModelProperty(value = "选中的助教", required = false)
    private String zhujiaoteacher;

    @ApiModelProperty(value = "上课日期", required = true)
    private Date shangkedate;

    @ApiModelProperty(value = "上课时间", required = true)
    private String startTime;

    @ApiModelProperty(value = "下课时间", required = true)
    private String endTime;


//    @ApiModelProperty(value = "课时数", required = true)
//    private String keshinum;
//
//    @ApiModelProperty(value = "批量消课 类型", required = false)
//    private String rashijian;

    @ApiModelProperty(value = "说明", required = false)
    private String shuoming;
}

