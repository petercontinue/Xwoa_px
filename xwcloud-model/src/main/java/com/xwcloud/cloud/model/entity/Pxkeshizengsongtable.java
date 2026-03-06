package com.xwcloud.cloud.model.entity;

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
 * @since 2020-11-23
 */
@Data
@Accessors(chain = true)
public class Pxkeshizengsongtable extends Model<Pxkeshizengsongtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 课程ID
     */
	@TableField("kechengID")
	private Long kechengID;
    /**
     * 课程单价
     */
	@TableField("kechengPrice")
	private BigDecimal kechengPrice;
    /**
     * 课时数
     */
	@TableField("keshiShu")
	private BigDecimal keshiShu;
    /**
     * 操作人
     */
	@TableField("caozuoStaffId")
	private Long caozuoStaffId;
    /**
     * 操作时间
     */
	@TableField("addDate")
	private Date addDate;
    /**
     * 赠送原因
     */
	@TableField("songYangyin")
	private String songYangyin;
    /**
     * 课程计费方式，1.按课时计费 2.按课时包计费 3.按起止日期计费
     */
	@TableField("JifeiStyle")
	private Integer JifeiStyle;
	@TableField("qiandanInfoID")
	private Long qiandanInfoID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxkeshizengsongtable{" +
			", id=" + id +
			", stuID=" + stuID +
			", kechengID=" + kechengID +
			", kechengPrice=" + kechengPrice +
			", keshiShu=" + keshiShu +
			", caozuoStaffId=" + caozuoStaffId +
			", addDate=" + addDate +
			", songYangyin=" + songYangyin +
			", JifeiStyle=" + JifeiStyle +
			", qiandanInfoID=" + qiandanInfoID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
