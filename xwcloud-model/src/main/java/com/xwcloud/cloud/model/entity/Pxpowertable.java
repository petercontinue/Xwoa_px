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
 * @since 2021-03-11
 */
@Data
@Accessors(chain = true)
@TableName("pxpowertable")
public class Pxpowertable extends Model<Pxpowertable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("staffpostID")
	private Long staffpostID;
	@TableField("menuID")
	private Long menuID;
	@TableField("dataFanwei")
	private String dataFanwei;
	@TableField("insertbtn")
	private Boolean insertbtn;
	@TableField("updatebtn")
	private Boolean updatebtn;
	@TableField("deletebtn")
	private Boolean deletebtn;
	@TableField("pringbtn")
	private Boolean pringbtn;
	@TableField("daorubtn")
	private Boolean daorubtn;
	@TableField("daochubtn")
	private Boolean daochubtn;
	@TableField("qitabtn")
	private String qitabtn;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxpowertable{" +
			", id=" + id +
			", staffpostID=" + staffpostID +
			", menuID=" + menuID +
			", dataFanwei=" + dataFanwei +
			", insertbtn=" + insertbtn +
			", updatebtn=" + updatebtn +
			", deletebtn=" + deletebtn +
			", pringbtn=" + pringbtn +
			", daorubtn=" + daorubtn +
			", daochubtn=" + daochubtn +
			", qitabtn=" + qitabtn +
			", qiyeID=" + qiyeID +
			"}";
	}
}
