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
 * @since 2021-02-22
 */
@Data
@Accessors(chain = true)
@TableName("pxtesttypetable")
public class Pxtesttypetable extends Model<Pxtesttypetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 考试类别
     */
	@TableField("testType")
	private String testType;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxtesttypetable{" +
			", id=" + id +
			", testType=" + testType +
			", qiyeID=" + qiyeID +
			"}";
	}
}
