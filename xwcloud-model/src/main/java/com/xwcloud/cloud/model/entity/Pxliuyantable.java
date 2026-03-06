package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-11-25
 */
@Data
@Accessors(chain = true)
@TableName("pxliuyantable")
public class Pxliuyantable extends Model<Pxliuyantable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 留言者姓名
     */
	@TableField("userName")
	private String userName;
    /**
     * 留言者联系电话
     */
	@TableField("tel")
	private String tel;
    /**
     * 意向校区ID
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 意向校区
     */
	@TableField("campusNames")
	private String campusNames;
    /**
     * 意向留言内容
     */
	@TableField("yixiangText")
	private String yixiangText;
    /**
     * 留言时间
     */
	@TableField("addDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date addDate;
	@TableField("qiyeID")
	private Long qiyeID;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxliuyantable{" +
			", id=" + id +
			", userName=" + userName +
			", tel=" + tel +
			", campusID=" + campusID +
			", campusNames=" + campusNames +
			", yixiangText=" + yixiangText +
			", addDate=" + addDate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
