package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-11-19
 */
@Data
@Accessors(chain = true)
@TableName("pxstucardtable")
public class Pxstucardtable extends Model<Pxstucardtable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	@TableField("stuID")
	private Long stuID;
	/**
	 * 学员卡的卡号
	 */
	@TableField("cardNumber")
	private String cardnumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addDate")
	private Date adddate;
	@TableField("addStaffID")
	private Long addStaffID;
	@TableField("qiyeID")
	private Long qiyeID;

}
