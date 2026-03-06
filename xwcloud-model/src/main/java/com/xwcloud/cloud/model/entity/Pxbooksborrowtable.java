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
public class Pxbooksborrowtable extends Model<Pxbooksborrowtable> {

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

	/*
	 * 借书数量，多少本
	 */
	@TableField("borrownum")
	private Integer borrownum;

	/*
	 * 还书日期
	 */
	@TableField("endDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	/*
	 * 借书工作人员
	 */
	@TableField("dostaffID")
	private Long dostaffID;

	/*
	 * 借书操作时间
	 */
	@TableField("doDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doDate;

	@TableField("beizhu")
	private String beizhu;

	@TableField("qiyeID")
	private Long qiyeID;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
