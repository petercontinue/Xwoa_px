package com.xwcloud.cloud.model.OA;

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
@TableName("oa_yingjianbuyrecord")
public class OaYingjianbuyrecord extends Model<OaYingjianbuyrecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 硬件ID
     */
    @TableField("yingjianID")
    private Long yingjianID;
    /**
     * 客户ID(oa_kehu里的id)
     */
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 签单ID
     */
    @TableField("qiandanID")
    private Long qiandanID;
    /**
     * 购买数量
     */
    @TableField("buyNum")
    private BigDecimal buyNum;
    /**
     * 单价
     */
    @TableField("price")
    private BigDecimal price;
    /**
     * 总金额
     */
    @TableField("totalMoney")
    private BigDecimal totalMoney;
    /**
     * 下单状态，0没买，1买了，默认值0
     */
    @TableField("xiadanState")
    private Integer xiadanState;
    /**
     * 硬件下单对应的财务流水ID
     */
    @TableField("yingjianLiushuiID")
    private Long yingjianLiushuiID;
    /**
     * 硬件下单操作人
     */
    @TableField("yingjianBuyUser")
    private Long yingjianBuyUser;
    /**
     * 硬件下单操作时间
     */
    @TableField("yingjianBuyTime")
    private Date yingjianBuyTime;
    /**
     * 淘宝订单ID
     */
    @TableField("taobaoID")
    private String taobaoID;
    @TableField("addUser")
    private Long addUser;
    @TableField("addTime")
    private Date addTime;
    /**
     * 备注说明
     */
    @TableField("shuoming")
    private String shuoming;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaYingjianbuyrecord{" +
                ", id=" + id +
                ", yingjianID=" + yingjianID +
                ", qiyeID=" + qiyeID +
                ", qiandanID=" + qiandanID +
                ", buyNum=" + buyNum +
                ", price=" + price +
                ", totalMoney=" + totalMoney +
                ", xiadanState=" + xiadanState +
                ", yingjianLiushuiID=" + yingjianLiushuiID +
                ", yingjianBuyUser=" + yingjianBuyUser +
                ", yingjianBuyTime=" + yingjianBuyTime +
                ", addUser=" + addUser +
                ", addTime=" + addTime +
                ", shuoming=" + shuoming +
                "}";
    }
}
