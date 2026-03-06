package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-08
 */
@Data
@Accessors(chain = true)
@TableName("pxqiandanothermoneytable")
public class Pxqiandanothermoneytable extends Model<Pxqiandanothermoneytable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("otherMoneyName")
	private String othermoneyname;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxqiandanothermoneytable{" +
			", id=" + id +
			", othermoneyname=" + othermoneyname +
			", qiyeid=" + qiyeID +
			"}";
	}
}
