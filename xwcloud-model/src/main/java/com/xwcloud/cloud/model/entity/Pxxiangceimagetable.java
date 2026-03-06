package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Data
@Accessors(chain = true)
public class Pxxiangceimagetable extends Model<Pxxiangceimagetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 相册ID
     */
	@TableField("xiangceid")
	private Long xiangceid;
    /**
     * 图片路径
     */
	@TableField("image")
	private String image;
    /**
     * 图片描述
     */
	@TableField("miaoshu")
	private String miaoshu;
    /**
     * 上传时间
     */
	@TableField("addTime")
	private Date addTime;
    /**
     * 上传人
     */
	@TableField("addStaffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long addStaffID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxxiangceimagetable{" +
			", id=" + id +
			", xiangceid=" + xiangceid +
			", image=" + image +
			", miaoshu=" + miaoshu +
			", addTime=" + addTime +
			", addStaffID=" + addStaffID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
