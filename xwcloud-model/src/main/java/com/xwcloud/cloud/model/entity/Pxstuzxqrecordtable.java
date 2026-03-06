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
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
public class Pxstuzxqrecordtable extends Model<Pxstuzxqrecordtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("stuID")
	private Long stuID;
    /**
     * 原校区名称
     */
	@TableField("oldCampusID")
	private Long oldCampusID;
    /**
     * 新校区名称
     */
	@TableField("newCampusID")
	private Long newCampusID;
    /**
     * 课程信息，一定要填写详细
     */
	@TableField("kcChangInfo")
	private String kcChangInfo;
	@TableField("addStaffID")
	private Long addStaffID;
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
		return "Pxstuzxqrecordtable{" +
			", id=" + id +
			", stuID=" + stuID +
			", oldCampusID=" + oldCampusID +
			", newCampusID=" + newCampusID +
			", kcChangInfo=" + kcChangInfo +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
