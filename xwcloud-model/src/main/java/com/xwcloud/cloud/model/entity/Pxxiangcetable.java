package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-11-04
 */
@Data
@Accessors(chain = true)
public class Pxxiangcetable extends Model<Pxxiangcetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("stuID")
	private Long stuID;
    /**
     * 相册标题
     */
	@TableField("title")
	private String title;
    /**
     * 相册描述
     */
	@TableField("miaoshu")
	private String miaoshu;
	@TableField("addStaffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long addStaffID;
	@TableField("addTime")
	@DateTimeFormat(pattern ="yyyy-MM-dd hh:mm:ss")
	private Date addTime;
    /**
     * type=2时，这里存的校区ID，type=3时，这里存班级ID
     */
	@TableField("typeparmID")
	private Long typeparmID;
    /**
     * null,1.学员相册   2.校区相册，3班级相册，....
     */
	@TableField("type")
	private Integer type;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxxiangcetable{" +
			", id=" + id +
			", stuID=" + stuID +
			", title=" + title +
			", miaoshu=" + miaoshu +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", typeparmID=" + typeparmID +
			", type=" + type +
			", qiyeID=" + qiyeID +
			"}";
	}
}
