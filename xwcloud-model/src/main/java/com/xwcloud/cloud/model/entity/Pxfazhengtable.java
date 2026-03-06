package com.xwcloud.cloud.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-02-26
 */
@Data
@Accessors(chain = true)
@TableName("pxfazhengtable")
public class Pxfazhengtable extends Model<Pxfazhengtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("Stuid")
	private Long stuid;
	@TableField("ZSid")
	private Long zSid;
	@TableField("FZstaff")
	private Long fZstaff;
	@TableField("FZdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fZdate;
	@TableField("FZImage")
	private String fZImage;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxfazhengtable{" +
			", id=" + id +
			", stuid=" + stuid +
			", zSid=" + zSid +
			", fZstaff=" + fZstaff +
			", fZdate=" + fZdate +
			", fZImage=" + fZImage +
			", qiyeID=" + qiyeID +
			"}";
	}
}
