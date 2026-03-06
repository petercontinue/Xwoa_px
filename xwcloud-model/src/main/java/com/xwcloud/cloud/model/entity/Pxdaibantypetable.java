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
 * @since 2021-07-29
 */
@Data
@Accessors(chain = true)
@TableName("pxdaibantypetable")
public class Pxdaibantypetable extends Model<Pxdaibantypetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("daibanTypeName")
	private String daibanTypeName;
    /**
     * 是否显示，1显示，0不显示，默认值1
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
		return "Pxdaibantypetable{" +
			", id=" + id +
			", daibanTypeName=" + daibanTypeName +
			", isShow=" + isShow +
			", qiyeID=" + qiyeID +
			"}";
	}
}
