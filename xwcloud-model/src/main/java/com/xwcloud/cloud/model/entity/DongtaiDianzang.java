package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * @since 2021-05-19
 */
@Data
@Accessors(chain = true)
@TableName("dongtai_dianzang")
public class DongtaiDianzang extends Model<DongtaiDianzang> {

    private static final long serialVersionUID = 1L;

	@JsonSerialize(using= ToStringSerializer.class)
    @TableId("id")
	private Long id;
    /**
     * 动态ID
     */
	@TableField("dongtaiID")
	private Long dongtaiID;
    /**
     * 点赞用户ID,wsc_user表的ID
     */
	@TableField("dianzanUserID")
	private Long dianzanUserID;
    /**
     * 点赞时间
     */
	@TableField("dianzangDatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dianzangDatetime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DongtaiDianzang{" +
			", id=" + id +
			", dongtaiID=" + dongtaiID +
			", dianzanUserID=" + dianzanUserID +
			", dianzangDatetime=" + dianzangDatetime +
			"}";
	}
}
