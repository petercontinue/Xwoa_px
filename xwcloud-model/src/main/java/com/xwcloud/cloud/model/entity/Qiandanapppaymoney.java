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
 * @since 2021-05-28
 */
@Data
@Accessors(chain = true)
@TableName("qiandanapppaymoney")
public class Qiandanapppaymoney extends Model<Qiandanapppaymoney> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 签单小程序支付表ID
     */
	@TableField("qiandanAppayID")
	private Long qiandanAppayID;
    /**
     * 支付方式ID
     */
	@TableField("paymoneystyleID")
	private Long paymoneystyleID;
    /**
     * 支付金额
     */
	@TableField("paymoney")
	private BigDecimal paymoney;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Qiandanapppaymoney{" +
			", id=" + id +
			", qiandanAppayID=" + qiandanAppayID +
			", paymoneystyleID=" + paymoneystyleID +
			", paymoney=" + paymoney +
			", qiyeID=" + qiyeID +
			"}";
	}
}
