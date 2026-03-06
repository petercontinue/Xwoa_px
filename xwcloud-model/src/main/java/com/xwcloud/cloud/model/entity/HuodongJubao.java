package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2021-08-21
 */
@Data
@Accessors(chain = true)
@TableName("huodong_jubao")
public class HuodongJubao extends Model<HuodongJubao> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 举报活动类型
     */
	@TableField("huodongType")
	private String huodongType;
    /**
     * 举报说明
     */
	@TableField("shuoming")
	private String shuoming;
    /**
     * 举报活动标题
     */
	@TableField("huodongtitle")
	private String huodongtitle;
    /**
     * 举报时间
     */
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
    /**
     * 举报活动ID
     */
	@TableField("huodongID")
	private Long huodongID;
    /**
     * 举报用户ID
     */
	@TableField("addUserID")
	private Long addUserID;
    /**
     * 举报状态：1：未处理；2：处理完成，默认值为1
     */
	@TableField("ischuli")
	private Integer ischuli;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "HuodongJubao{" +
			", id=" + id +
			", huodongType=" + huodongType +
			", shuoming=" + shuoming +
			", huodongtitle=" + huodongtitle +
			", addTime=" + addTime +
			", huodongID=" + huodongID +
			", addUserID=" + addUserID +
			", ischuli=" + ischuli +
			", qiyeID=" + qiyeID +
			"}";
	}
}
