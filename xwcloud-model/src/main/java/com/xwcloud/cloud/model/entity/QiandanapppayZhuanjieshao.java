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
 * @since 2021-07-26
 */
@Data
@Accessors(chain = true)
@TableName("qiandanapppayZhuanjieshao")
public class QiandanapppayZhuanjieshao extends Model<QiandanapppayZhuanjieshao> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 小程序签单支付ID
     */
	@TableField("qiandanapppayID")
	private Long qiandanapppayID;
    /**
     * 转介绍学员ID，即是哪个学员转介绍的
     */
	@TableField("zhuanjieshaoFromStuID")
	private Long zhuanjieshaoFromStuID;
    /**
     * 是哪个老师促成的转介绍
     */
	@TableField("zhuanjieshaoFromStaffID")
	private Long zhuanjieshaoFromStaffID;
    /**
     * 转介绍备注，说明
     */
	@TableField("beizhu")
	private String beizhu;
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
		return "QiandanapppayZhuanjieshao{" +
			", id=" + id +
			", qiandanapppayID=" + qiandanapppayID +
			", zhuanjieshaoFromStuID=" + zhuanjieshaoFromStuID +
			", zhuanjieshaoFromStaffID=" + zhuanjieshaoFromStaffID +
			", beizhu=" + beizhu +
			", qiyeID=" + qiyeID +
			"}";
	}
}
