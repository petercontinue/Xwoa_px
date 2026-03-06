package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
@Data
public class Pxminimumchargetable extends Model<Pxminimumchargetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 补习方式
     */
	@TableField("buxiStyleId")
	private Long buxiStyleId;
    /**
     * 年级
     */
	@TableField("stuGradeId")
	private Long stuGradeId;
    /**
     * 最低价格,没设置表示不限制
     */
	@TableField("MinimumCharge")
	private BigDecimal MinimumCharge;
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@TableField("addStaffID")
	private Long addStaffID;

	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxminimumchargetable{" +
			", id=" + id +
			", buxiStyleId=" + buxiStyleId +
			", stuGradeId=" + stuGradeId +
			", MinimumCharge=" + MinimumCharge +
			", addTime=" + addTime +
			", addStaffID=" + addStaffID +
			"}";
	}
}
