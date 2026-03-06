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
@TableName("wsc_huodong")
public class WscHuodong extends Model<WscHuodong> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 微活动名称
     */
	@TableField("huodongName")
	private String huodongName;
    /**
     * 1显示，0不显示，默认1
     */
	@TableField("isShow")
	private Integer isShow;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscHuodong{" +
			", id=" + id +
			", huodongName=" + huodongName +
			", isShow=" + isShow +
			"}";
	}
}
