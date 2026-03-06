package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-11-12
 */
@Data
@Accessors(chain = true)
public class Pxgonggaojiazhangtable extends Model<Pxgonggaojiazhangtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("bianLiangName")
	private String bianLiangName;
	@TableField("modifyValue")
	private String modifyValue;
	@TableField("ParameterContent")
	private String ParameterContent;
	@TableField("type")
	private String type;
	@TableField("tianjiastaff")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long tianjiastaff;
	@TableField("tianjiashijian")
	private Date tianjiashijian;
	@TableField("qiyeID")
	private long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxgonggaojiazhangtable{" +
			", id=" + id +
			", bianLiangName=" + bianLiangName +
			", modifyValue=" + modifyValue +
			", ParameterContent=" + ParameterContent +
			", type=" + type +
			", tianjiastaff=" + tianjiastaff +
			", tianjiashijian=" + tianjiashijian +
			", qiyeID=" + qiyeID +
			"}";
	}
}
