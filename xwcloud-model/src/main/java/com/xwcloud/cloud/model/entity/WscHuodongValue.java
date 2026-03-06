package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-05-20
 */
@Data
@Accessors(chain = true)
@TableName("wsc_huodong_value")
public class WscHuodongValue extends Model<WscHuodongValue> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 活动ID，对应wsc_huodong表的id
     */
	@TableField("huodongID")
	private Long huodongID;
    /**
     * 排序
     */
	@TableField("paixu")
	private Integer paixu;
    /**
     * 1显示，0不显示，默认1
     */
	@TableField("isShow")
	private Integer isShow;
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
		return "WscHuodongValue{" +
			", id=" + id +
			", huodongID=" + huodongID +
			", paixu=" + paixu +
			", isShow=" + isShow +
			", qiyeID=" + qiyeID +
			"}";
	}
}
