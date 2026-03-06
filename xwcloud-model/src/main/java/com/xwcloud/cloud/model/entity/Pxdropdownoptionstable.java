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
 * @since 2021-03-08
 */
@Data
@Accessors(chain = true)
@TableName("pxdropdownoptionstable")
public class Pxdropdownoptionstable extends Model<Pxdropdownoptionstable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 学生自定义属性类型ID
     */
	@TableField("stuParamTypeId")
	private Long stuParamTypeId;
    /**
     * 下拉选项名称
     */
	@TableField("DropDownOptions")
	private String dropDownOptions;
    /**
     * 是否启用，1启用，0不启用，默认值1
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
		return "Pxdropdownoptionstable{" +
			", id=" + id +
			", stuParamTypeId=" + stuParamTypeId +
			", dropDownOptions=" + dropDownOptions +
			", isShow=" + isShow +
			", qiyeID=" + qiyeID +
			"}";
	}
}
