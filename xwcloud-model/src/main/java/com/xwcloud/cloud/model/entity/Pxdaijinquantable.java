package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-04-12
 */
@Data
@Accessors(chain = true)
@TableName("pxdaijinquantable")
public class Pxdaijinquantable extends Model<Pxdaijinquantable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("stuID")
	private Long stuID;
	@TableField("qiandanID")
	private Long qiandanID;
    /**
     * 代金券金额
     */
	@TableField("money")
	private BigDecimal money;
	@TableField("creatTime")
	private Date creatTime;
	@TableField("staffID")
	private Long staffID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxdaijinquantable{" +
			", id=" + id +
			", stuID=" + stuID +
			", qiandanID=" + qiandanID +
			", money=" + money +
			", creatTime=" + creatTime +
			", staffID=" + staffID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
