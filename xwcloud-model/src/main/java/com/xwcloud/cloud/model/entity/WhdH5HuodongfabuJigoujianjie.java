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
 * @since 2021-04-02
 */
@Data
@Accessors(chain = true)
@TableName("whd_h5_huodongfabu_jigoujianjie")
public class WhdH5HuodongfabuJigoujianjie extends Model<WhdH5HuodongfabuJigoujianjie> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 简介内容
     */
	@TableField("jianjieNeirong")
	private String jianjieNeirong;
    /**
     * 简介类型：0文字，1图片，2视频链接
     */
	@TableField("jianjieType")
	private Integer jianjieType;
    /**
     * 排序
     */
	@TableField("jiajiePaixu")
	private Integer jiajiePaixu;
    /**
     * 机构信息模板id,对应whd_h5_huodongfabu表的id
     */
	@TableField("whd_h5_huodongfabu_id")
	private Long whdH5HuodongfabuId;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdH5HuodongfabuJigoujianjie{" +
			", id=" + id +
			", jianjieNeirong=" + jianjieNeirong +
			", jianjieType=" + jianjieType +
			", jiajiePaixu=" + jiajiePaixu +
			", whdH5HuodongfabuId=" + whdH5HuodongfabuId +
			", qiyeID=" + qiyeID +
			"}";
	}
}
