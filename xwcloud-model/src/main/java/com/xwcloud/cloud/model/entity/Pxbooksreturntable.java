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
 * @since 2020-11-13
 */
@Data
@Accessors(chain = true)
public class Pxbooksreturntable extends Model<Pxbooksreturntable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;

	@TableField("booksID")
	private Long booksID;

	/*
	 * 借书人
	 */
	@TableField("people")
	private Long people;
	/*
	 * 借书人身份角色，1教师,2学生
	 */
	@TableField("role")
	private Integer role;
	@TableField("returnnum")
	private Integer returnnum;
	@TableField("returnDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date returnDate;
	@TableField("dostaffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long dostaffID;
	@TableField("doDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date doDate;
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
		return "Pxbooksreturntable{" +
			", id=" + id +
			", booksID=" + booksID +
			", people=" + people +
			", role=" + role +
			", returnnum=" + returnnum +
			", returnDate=" + returnDate +
			", dostaffID=" + dostaffID +
			", doDate=" + doDate +
			", beizhu=" + beizhu +
			", qiyeID=" + qiyeID +
			"}";
	}
}
