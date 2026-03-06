package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-11-04
 */
@Data
@Accessors(chain = true)
public class Pxroomknowingtable extends Model<Pxroomknowingtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 宿舍ID
     */
	@TableField("roomID")
	private String roomID;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private String stuID;
    /**
     * 床位,学生在哪个床位
     */
	@TableField("stubed")
	private String stubed;
	@TableField("beizhu")
	private String beizhu;
    /**
     * 添加人
     */
	@TableField("addStaffID")
	private String addStaffID;
    /**
     * 添加时间
     */
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
		return "Pxroomknowingtable{" +
			", id=" + id +
			", roomID=" + roomID +
			", stuID=" + stuID +
			", stubed=" + stubed +
			", beizhu=" + beizhu +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
