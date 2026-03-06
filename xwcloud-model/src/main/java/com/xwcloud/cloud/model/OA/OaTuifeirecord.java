package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-01
 */
@Data
@Accessors(chain = true)
@TableName("oa_tuifeirecord")
public class OaTuifeirecord extends Model<OaTuifeirecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("operatetuifeiDatetime")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatetuifeiDatetime;
    @TableField("qiyeID")
    private Long qiyeID;
    @TableField("qiandanID")
    private Long qiandanID;
    @TableField("salestaffID")
    private Long salestaffID;
    @TableField("tuifeiDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tuifeiDate;
    @TableField("tuifeiMoney")
    private BigDecimal tuifeiMoney;
    @TableField("tuifeiReason")
    private String tuifeiReason;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaTuifeirecord{" +
                ", id=" + id +
                ", operatetuifeiDatetime=" + operatetuifeiDatetime +
                ", qiyeID=" + qiyeID +
                ", qiandanID=" + qiandanID +
                ", salestaffID=" + salestaffID +
                ", tuifeiDate=" + tuifeiDate +
                ", tuifeiMoney=" + tuifeiMoney +
                ", tuifeiReason=" + tuifeiReason +
                "}";
    }
}
