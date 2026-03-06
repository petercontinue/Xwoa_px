package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
@Data
@Accessors(chain = true)
@TableName("qiandanshenpizafei")
public class Qiandanshenpizafei extends Model<Qiandanshenpizafei> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("qiandanshenpiID")
    private Long qiandanshenpiID;
    /**
     * 签单杂费ID
     */
    @TableField("qianDanOtherMoneyID")
    private Long qianDanOtherMoneyID;
    /**
     * 单价
     */
    @TableField("onePrice")
    private BigDecimal onePrice;
    /**
     * 购买数量
     */
    @TableField("nums")
    private BigDecimal nums;
    /**
     * 总价
     */
    @TableField("zongMoney")
    private BigDecimal zongMoney;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Qiandanshenpizafei{" +
                ", id=" + id +
                ",qiandanshenpiID=" + qiandanshenpiID +
                ", qianDanOtherMoneyID=" + qianDanOtherMoneyID +
                ", onePrice=" + onePrice +
                ", nums=" + nums +
                ", zongMoney=" + zongMoney +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
