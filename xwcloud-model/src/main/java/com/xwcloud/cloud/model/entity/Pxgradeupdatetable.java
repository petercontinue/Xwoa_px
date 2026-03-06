package com.xwcloud.cloud.model.entity;

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
 * @since 2020-11-18
 */
@Data
@Accessors(chain = true)
public class Pxgradeupdatetable extends Model<Pxgradeupdatetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("stuID")
	private Long stuID;
	@TableField("oldgrade")
	private Long oldgrade;
	@TableField("nowgrade")
	private Long nowgrade;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addDate")
	private Date addDate;
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
		return "Pxgradeupdatetable{" +
			", id=" + id +
			", stuID=" + stuID +
			", oldgrade=" + oldgrade +
			", nowgrade=" + nowgrade +
			", addDate=" + addDate +
			", addStaffID=" + addStaffID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
