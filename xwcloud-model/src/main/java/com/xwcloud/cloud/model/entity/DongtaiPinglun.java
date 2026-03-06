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
@TableName("dongtai_pinglun")
public class DongtaiPinglun extends Model<DongtaiPinglun> {

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
     * 评论用户ID；wsc_user表的ID
     */
	@TableField("pluserID")
	private Long pluserID;
    /**
     * 评论内容
     */
	@TableField("plcontent")
	private String plcontent;
    /**
     * 评论时间
     */
	@TableField("pinglunDatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pinglunDatetime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DongtaiPinglun{" +
			", id=" + id +
			", dongtaiID=" + dongtaiID +
			", pluserID=" + pluserID +
			", plcontent=" + plcontent +
			", pinglunDatetime=" + pinglunDatetime +
			"}";
	}
}
