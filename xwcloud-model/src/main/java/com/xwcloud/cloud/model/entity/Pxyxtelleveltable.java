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
 * @since 2021-06-08
 */
@Data
@Accessors(chain = true)
@TableName("pxyxtelleveltable")
public class Pxyxtelleveltable extends Model<Pxyxtelleveltable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 意向程度名称
     */
	@TableField("telLevelName")
	private String telLevelName;
	@TableField("beizhu")
	private String beizhu;
	@TableField("addStaffID")
	private Long addStaffID;
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxyxtelleveltable{" +
			", id=" + id +
			", telLevelName=" + telLevelName +
			", beizhu=" + beizhu +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
