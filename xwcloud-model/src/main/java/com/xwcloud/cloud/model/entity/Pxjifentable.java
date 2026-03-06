package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;

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
@TableName("pxjifentable")
public class Pxjifentable extends Model<Pxjifentable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	@TableField("stuID")
	private Long stuID;
	/**
	 * 1增加  2扣减
	 */
	@TableField("type")
	private Integer type;
	/**
	 * 变动前原有积分
	 */
	@TableField("oldIntegral")
	private BigDecimal oldintegral;
	/**
	 * 变动积分数
	 */
	@TableField("integral")
	private BigDecimal integral;
	@TableField("staffID")
	private Long staffID;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("createTime")
	private Date createtime;
	/**
	 * 备注说明
	 */
	@TableField("remark")
	private String remark;
	@TableField("qiyeID")
	private long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
