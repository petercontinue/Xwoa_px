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
 * @since 2020-11-29
 */
@Data
@Accessors(chain = true)
public class Pxstukxqtable extends Model<Pxstukxqtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 学员的补习课程ID
     */
	@TableField("bxkcID")
	private Long bxkcID;
    /**
     * 所跨校区的课程ID
     */
	@TableField("kcID")
	private Long kcID;
    /**
     * 跨到哪个校区
     */
	@TableField("kxqCampusID")
	private Long kxqCampusID;
    /**
     * 添加人
     */
	@TableField("addStaffID")
	private Long addStaffID;
    /**
     * 添加时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addTime")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxstukxqtable{" +
			", id=" + id +
			", stuID=" + stuID +
			", bxkcID=" + bxkcID +
			", kcID=" + kcID +
			", kxqCampusID=" + kxqCampusID +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
