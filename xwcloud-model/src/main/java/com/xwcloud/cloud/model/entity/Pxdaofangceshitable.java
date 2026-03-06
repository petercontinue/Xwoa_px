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
 * @since 2021-08-25
 */
@Data
@Accessors(chain = true)
@TableName("pxdaofangceshitable")
public class Pxdaofangceshitable extends Model<Pxdaofangceshitable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("daofangID")
	private Long daofangID;
	@TableField("ceshiSubjectID")
	private Long ceshiSubjectID;
	@TableField("ceshiScore")
	private BigDecimal ceshiScore;
	@TableField("addStaffID")
	private Long addStaffID;
	@TableField("addTime")
	private Date addTime;
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
		return "Pxdaofangceshitable{" +
			", id=" + id +
			", daofangID=" + daofangID +
			", ceshiSubjectID=" + ceshiSubjectID +
			", ceshiScore=" + ceshiScore +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
