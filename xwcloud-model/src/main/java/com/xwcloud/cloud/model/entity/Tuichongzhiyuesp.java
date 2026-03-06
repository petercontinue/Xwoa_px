package com.xwcloud.cloud.model.entity;

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
 * @since 2021-07-16
 */
@Data
@Accessors(chain = true)
@TableName("tuichongzhiyuesp")
public class Tuichongzhiyuesp extends Model<Tuichongzhiyuesp> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 退费审批ID
     */
	@TableField("tfspID")
	private Long tfspID;
    /**
     * 退之前
     */
	@TableField("beforetuiMoney")
	private BigDecimal beforetuiMoney;
    /**
     * 退余额
     */
	@TableField("tuiyue")
	private BigDecimal tuiyue;
    /**
     * 退之后
     */
	@TableField("aftertuiMoney")
	private BigDecimal aftertuiMoney;
    /**
     * 添加时间
     */
	@TableField("addTime")
	private Date addTime;
    /**
     * 添加人
     */
	@TableField("addUser")
	private Long addUser;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Tuichongzhiyuesp{" +
			", id=" + id +
			", tfspID=" + tfspID +
			", beforetuiMoney=" + beforetuiMoney +
			", tuiyue=" + tuiyue +
			", aftertuiMoney=" + aftertuiMoney +
			", addTime=" + addTime +
			", addUser=" + addUser +
			", qiyeID=" + qiyeID +
			"}";
	}
}
