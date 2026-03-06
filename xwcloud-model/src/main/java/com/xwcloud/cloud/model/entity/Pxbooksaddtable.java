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
 * @since 2020-11-13
 */
@Data
@Accessors(chain = true)
public class Pxbooksaddtable extends Model<Pxbooksaddtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("booksID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long booksID;
	@TableField("addnum")
	private Integer addnum;
	@TableField("addstaffID")
	private String addstaffID;
	@TableField("addDate")
	private Date addDate;
	@TableField("beizhu")
	private String beizhu;
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxbooksaddtable{" +
			", id=" + id +
			", booksID=" + booksID +
			", addnum=" + addnum +
			", addstaffID=" + addstaffID +
			", addDate=" + addDate +
			", beizhu=" + beizhu +
			", qiyeID=" + qiyeID +
			"}";
	}
}
