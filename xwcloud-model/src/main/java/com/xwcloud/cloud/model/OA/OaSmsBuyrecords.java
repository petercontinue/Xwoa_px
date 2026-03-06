package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-07-03
 */
@Data
@Accessors(chain = true)
@TableName("oa_sms_buyrecords")
public class OaSmsBuyrecords extends Model<OaSmsBuyrecords> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 购买短信条数
     */
    @TableField("buySum")
    private Integer buySum;
    /**
     * 购买短信单价
     */
    @TableField("price")
    private BigDecimal price;
    /**
     * 购买短信总金额
     */
    @TableField("sumMoney")
    private BigDecimal sumMoney;
    /**
     * 购买时间
     */
    @TableField("buyTime")
    private Date buyTime;
    @TableField("addTime")
    private Date addTime;
    @TableField("addUser")
    private Long addUser;
    @TableField("shuoming")
    private String shuoming;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaSmsBuyrecords{" +
                ", id=" + id +
                ", qiyeID=" + qiyeID +
                ", buySum=" + buySum +
                ", price=" + price +
                ", sumMoney=" + sumMoney +
                ", buyTime=" + buyTime +
                ", addTime=" + addTime +
                ", addUser=" + addUser +
                "}";
    }
}
