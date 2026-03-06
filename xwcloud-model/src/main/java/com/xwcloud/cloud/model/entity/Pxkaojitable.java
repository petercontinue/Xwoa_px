package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
public class Pxkaojitable extends Model<Pxkaojitable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 学员ID
     */
	@TableField("stuid")
	private Long stuid;
    /**
     * 科目ID
     */
	@TableField("kemuid")
	private Long kemuid;
    /**
     * 级别
     */
	@TableField("jibie")
	private String jibie;
    /**
     * 添加时间
     */
	@TableField("time")
	private Date time;
    /**
     * 添加人
     */
	@TableField("addsatff")
	private Long addsatff;
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxkaojitable{" +
			", id=" + id +
			", stuid=" + stuid +
			", kemuid=" + kemuid +
			", jibie=" + jibie +
			", time=" + time +
			", addsatff=" + addsatff +
			", qiyeID=" + qiyeID +
			"}";
	}
}
