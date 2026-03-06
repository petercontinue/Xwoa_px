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
 * @since 2021-04-16
 */
@Data
@Accessors(chain = true)
@TableName("tuiqiandaninfo2")
public class Tuiqiandaninfo2 extends Model<Tuiqiandaninfo2> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	/**
	 * 退费ID
	 */
	@TableField("tuifeiID")
	private Long tuifeiID;
	/**
	 * 退杂费表ID
	 */
	@TableField("tuiqianInfo2ID")
	private Long tuiqianInfo2ID;
	/**
	 * 退费前的已退费金额
	 */
	@TableField("beforetuiMoney")
	private BigDecimal beforetuiMoney;
	/**
	 * 退费后的已退费金额
	 */
	@TableField("aftertuiMoney")
	private BigDecimal aftertuiMoney;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Tuiqiandaninfo2{" +
				", id=" + id +
				", tuifeiID=" + tuifeiID +
				", tuiqianInfo2ID=" + tuiqianInfo2ID +
				", beforetuiMoney=" + beforetuiMoney +
				", aftertuiMoney=" + aftertuiMoney +
				", qiyeID=" + qiyeID +
				"}";
	}
}