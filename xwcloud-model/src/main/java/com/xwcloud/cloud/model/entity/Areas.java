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
 * @since 2021-08-17
 */
@Data
@Accessors(chain = true)
@TableName("areas")
public class Areas extends Model<Areas> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private String id;
	@TableField("arealevel")
	private Integer arealevel;
	@TableField("areaname")
	private String areaname;
	@TableField("lat")
	private String lat;
	@TableField("lng")
	private String lng;
	@TableField("parentid")
	private String parentid;
	@TableField("position")
	private String position;
	@TableField("shortname")
	private String shortname;
	@TableField("sort")
	private Integer sort;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Areas{" +
			", id=" + id +
			", arealevel=" + arealevel +
			", areaname=" + areaname +
			", lat=" + lat +
			", lng=" + lng +
			", parentid=" + parentid +
			", position=" + position +
			", shortname=" + shortname +
			", sort=" + sort +
			"}";
	}
}
