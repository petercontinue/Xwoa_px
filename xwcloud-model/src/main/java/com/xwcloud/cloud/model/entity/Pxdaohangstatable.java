package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-30
 */
@Data
@Accessors(chain = true)
@TableName("pxdaohangstatable")
public class Pxdaohangstatable extends Model<Pxdaohangstatable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("staffID")
	private Long staffID;
	@TableField("dhID")
	private Long dhID;
	@TableField("paixu")
	private Integer paixu;
    /**
     * 是否显示，1显示，0不显示，默认值1
     */
	@TableField("isShow")
	private Integer isShow;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxdaohangstatable{" +
			", id=" + id +
			", staffID=" + staffID +
			", dhID=" + dhID +
			", paixu=" + paixu +
			", isShow=" + isShow +
			", qiyeID=" + qiyeID +
			"}";
	}
}
