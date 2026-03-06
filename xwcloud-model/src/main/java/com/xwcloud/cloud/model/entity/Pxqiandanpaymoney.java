package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-05
 */
@Data
@Accessors(chain = true)
@TableName("pxqiandanpaymoney")
public class Pxqiandanpaymoney extends Model<Pxqiandanpaymoney> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 签单ID
     */
	@TableField("qiandanID")
	private Long qiandanID;
    /**
     * 支付方式，关联pxpaymoneystyletable表的ID
	 * 小程序支付-1，充值余额支付-2
     */
	@TableField("paymoneyStyleID")
	private Long paymoneyStyleID;
    /**
     * 支付金额
     */
	@TableField("payMoney")
	private BigDecimal payMoney;
    /**
     * 签单日期
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("qianDanDate")
	private Date qianDanDate;
    /**
     * 是否是尾款，0不是尾款，1是尾款，默认值0
     */
	@TableField("isWeikuan")
	private Integer isWeikuan;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxqiandanpaymoney{" +
			", id=" + id +
			", qiandanID=" + qiandanID +
			", paymoneyStyleID=" + paymoneyStyleID +
			", payMoney=" + payMoney +
			", qianDanDate=" + qianDanDate +
			", isWeikuan=" + isWeikuan +
			", qiyeID=" + qiyeID +
			"}";
	}
}
