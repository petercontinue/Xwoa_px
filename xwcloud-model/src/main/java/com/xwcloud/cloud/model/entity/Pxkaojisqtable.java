package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
public class Pxkaojisqtable extends Model<Pxkaojisqtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 学生ID
     */
	@TableField("stuid")
	private Long stuid;
    /**
     * 科目ID
     */
	@TableField("kemuid")
	private Long kemuid;
    /**
     * 申请级别
     */
	@TableField("sqjibie")
	private String sqjibie;
    /**
     * 审核级别
     */
	@TableField("shjibie")
	private String shjibie;
    /**
     * 录入人
     */
	@TableField("lururen")
	private Long lururen;
    /**
     * 录入时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addDate")
	private Date addDate;
    /**
     * 审核人
     */
	@TableField("shenheren")
	private Long shenheren;
    /**
     * 审核时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("shenheDate")
	private Date shenheDate;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxkaojisqtable{" +
			", id=" + id +
			", stuid=" + stuid +
			", kemuid=" + kemuid +
			", sqjibie=" + sqjibie +
			", shjibie=" + shjibie +
			", lururen=" + lururen +
			", addDate=" + addDate +
			", shenheren=" + shenheren +
			", shenheDate=" + shenheDate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
