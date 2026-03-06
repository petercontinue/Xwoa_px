package com.xwcloud.cloud.model.entity;

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
 * @since 2020-11-11
 */
@Data
@Accessors(chain = true)
public class Pxqiandanzhuanjieshaotable extends Model<Pxqiandanzhuanjieshaotable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 签单ID
     */
	@TableField("qiandanID")
	private Long qiandanID;
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
		return "Pxqiandanzhuanjieshaotable{" +
			", id=" + id +
			", qiandanID=" + qiandanID +
			", zhuanjieshaoFromStuID=" + zhuanjieshaoFromStuID +
			", zhuanjieshaoFromStaffID=" + zhuanjieshaoFromStaffID +
			", beizhu=" + beizhu +
			", qiyeID=" + qiyeID +
			"}";
	}
}
