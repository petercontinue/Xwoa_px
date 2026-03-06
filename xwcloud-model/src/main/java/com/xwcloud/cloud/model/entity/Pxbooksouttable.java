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
public class Pxbooksouttable extends Model<Pxbooksouttable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("booksID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long booksID;
	@TableField("outnum")
	private Integer outnum;
	@TableField("outstaffID")
	private String outstaffID;
	@TableField("outDate")
	private Date outDate;
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
		return "Pxbooksouttable{" +
			", id=" + id +
			", booksID=" + booksID +
			", outnum=" + outnum +
			", outstaffID=" + outstaffID +
			", outDate=" + outDate +
			", beizhu=" + beizhu +
			", qiyeID=" + qiyeID +
			"}";
	}
}
