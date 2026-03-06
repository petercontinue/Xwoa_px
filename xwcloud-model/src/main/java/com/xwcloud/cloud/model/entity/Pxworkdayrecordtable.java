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
 * @since 2020-10-25
 */
@Data
@Accessors(chain = true)
public class Pxworkdayrecordtable extends Model<Pxworkdayrecordtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("staffID")
	private Long staffID;
    /**
     * 日志日期
     */
	@TableField("LogDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date LogDate;
    /**
     * 日志内容
     */
	@TableField("LogContent")
	private String LogContent;
    /**
     * 日志图片
     */
	@TableField("ImgsUrl")
	private String ImgsUrl;
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxworkdayrecordtable{" +
			", id=" + id +
			", staffID=" + staffID +
			", LogDate=" + LogDate +
			", LogContent=" + LogContent +
			", ImgsUrl=" + ImgsUrl +
			", qiyeID=" + qiyeID +
			"}";
	}
}
