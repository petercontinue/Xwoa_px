package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("qiandanapppayyejiren")
public class Qiandanapppayyejiren extends Model<Qiandanapppayyejiren> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 签单ApppayID
     */
	@TableField("qiandanAppayID")
	private Long qiandanAppayID;
    /**
     * 签单业绩人ID
     */
	@TableField("qiandanstaffID")
	private Long qiandanstaffID;
    /**
     * 签单业绩人业绩金额
     */
	@TableField("yejiMoney")
	private BigDecimal yejiMoney;
    /**
     * 签单日期
     */
	@TableField("yejidate")
	private Date yejidate;
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
		return "Qiandanapppayyejiren{" +
			", id=" + id +
			", qiandanAppayID=" + qiandanAppayID +
			", qiandanstaffID=" + qiandanstaffID +
			", yejiMoney=" + yejiMoney +
			", yejidate=" + yejidate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
