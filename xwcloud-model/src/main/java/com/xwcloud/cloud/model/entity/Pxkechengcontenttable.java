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
 * @since 2020-11-11
 */
@Data
@Accessors(chain = true)
public class Pxkechengcontenttable extends Model<Pxkechengcontenttable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("kechengContent")
	private String kechengContent;
	@TableField("contentPaixu")
	private Integer contentPaixu;
	@TableField("kechengID")
	private Long kechengID;

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
		return "Pxkechengcontenttable{" +
			", id=" + id +
			", kechengContent=" + kechengContent +
			", contentPaixu=" + contentPaixu +
			", kechengID=" + kechengID +
			", qiyeID=" + qiyeID +
			", addTime=" + addTime +
			", addStaffID=" + addStaffID +
			"}";
	}
}
