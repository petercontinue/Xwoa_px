package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新签提交信息类
 */
@Data
public class weikuanForm {
    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "签单ID")
    public String qdid;
    /**
     * 校区ID
     */
    @ApiModelProperty(value = "金额")
    public BigDecimal jine;
    /**
     * 补交尾款日期
     */
    @ApiModelProperty(value = "补交尾款日期")
    public String bujiaodate;

    @ApiModelProperty(value = "支付方式")
    public String paymoneystyle;

    @ApiModelProperty(value = "业绩信息")
    public String yejimessage;

}
