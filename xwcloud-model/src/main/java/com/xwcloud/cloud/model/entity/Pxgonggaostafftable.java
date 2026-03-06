package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-23
 */
@Data
@Accessors(chain = true)
@TableName("pxgonggaostafftable")
public class Pxgonggaostafftable extends Model<Pxgonggaostafftable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("gonggaoId")
	private Long gonggaoid;
	@TableField("jieshouDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date jieshoudate;
	@TableField("IsRead")
	private Boolean isread;
	@TableField("staffID")
	private Long staffid;
	@TableField("qiyeID")
	private Long qiyeid;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxgonggaostafftable{" +
			", id=" + id +
			", gonggaoid=" + gonggaoid +
			", jieshoudate=" + jieshoudate +
			", isread=" + isread +
			", staffid=" + staffid +
			", qiyeid=" + qiyeid +
			"}";
	}
}
