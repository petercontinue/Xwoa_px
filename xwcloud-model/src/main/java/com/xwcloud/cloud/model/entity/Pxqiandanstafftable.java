package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-26
 */
@Data
@Accessors(chain = true)

public class Pxqiandanstafftable extends Model<Pxqiandanstafftable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("qiandanID")
	private Long qiandanID;
	@TableField("staffID")
	private Long staffID;
    /**
     * 业绩金额
     */
	@TableField("yejiMoney")
	private BigDecimal yejiMoney;
    /**
     * 业绩日期
     */
	@TableField("yejidateTime")
	private LocalDateTime yejidateTime;
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
		return "Pxqiandanstafftable{" +
			", id=" + id +
			", qiandanID=" + qiandanID +
			", staffID=" + staffID +
			", yejiMoney=" + yejiMoney +
			", yejidateTime=" + yejidateTime +
			", isWeikuan=" + isWeikuan +
			", qiyeID=" + qiyeID +
			"}";
	}
}
