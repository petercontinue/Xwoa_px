package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Data
/**
 * 排课消课表达
 */
public class paikexiaokeFrom {
    @ApiModelProperty(value = "tiankc", required = true)
    private String tiankc;

    @ApiModelProperty(value = "学员消课数据", required = true)
    private String studatamesaage;

    @ApiModelProperty(value = "计入教师课时数", required = true)
    private BigDecimal teacherKeshiNum;

    @ApiModelProperty(value = "助教", required = false)
    private String zhujiao;

}
