package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
public class Pxcertificatetable extends Model<Pxcertificatetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("zsName")
	private String zsName;
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxcertificatetable{" +
			", id=" + id +
			", zsName=" + zsName +
			", qiyeID=" + qiyeID +
			"}";
	}
}
