package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-11-10
 */
@Data
@Accessors(chain = true)
public class Pxstuhuifangtable extends Model<Pxstuhuifangtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 学员ID或意向学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 1意向学员回访，2老学员回访
     */
	@TableField("hfType")
	private Integer hfType;
    /**
     * 回访内容
     */
	@TableField("text")
	private String text;
    /**
     * 回访老师ID
     */
	@TableField("hfstaffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long hfstaffID;
    /**
     * 回访时间
     */
	@TableField("huifangTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date huifangTime;
    /**
     * 录入时间
     */
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
    /**
     * 录入人
     */
	@TableField("addstaffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long addstaffID;
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
		return "Pxstuhuifangtable{" +
			", id=" + id +
			", stuID=" + stuID +
			", hfType=" + hfType +
			", text=" + text +
			", hfstaffID=" + hfstaffID +
			", huifangTime=" + huifangTime +
			", addTime=" + addTime +
			", addstaffID=" + addstaffID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
