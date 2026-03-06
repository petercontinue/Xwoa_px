package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-18
 */
@Data
@Accessors(chain = true)
@TableName("birthdaydianzang")
public class Birthdaydianzang extends Model<Birthdaydianzang> {

    private static final long serialVersionUID = 1L;

	@JsonSerialize(using= ToStringSerializer.class)
    @TableId("id")
	private Long id;
    /**
     * 点赞用户ID	
     */
	@TableField("dianzanUserID")
	private Long dianzanUserID;
    /**
     * 被点赞用户ID（pxstutable表的ID或者pxstafftable表的ID）
     */
	@TableField("beidianzanUserID")
	private Long beidianzanUserID;
    /**
     * 点赞时间
     */
	@TableField("dianzanDatetime")
	private LocalDateTime dianzanDatetime;
    /**
     * 类型：1：教师，2：学生
     */
	@TableField("type")
	private Integer type;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Birthdaydianzang{" +
			", id=" + id +
			", dianzanUserID=" + dianzanUserID +
			", beidianzanUserID=" + beidianzanUserID +
			", dianzanDatetime=" + dianzanDatetime +
			", type=" + type +
			"}";
	}
}
