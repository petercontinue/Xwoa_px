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
 * @since 2020-11-25
 */
@Data
@Accessors(chain = true)
public class Pxsalarystyletable extends Model<Pxsalarystyletable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 工资类别
     */
	@TableField("salaryStyle")
	private String salaryStyle;
    /**
     * 是加项还是减项
     */
	@TableField("isJiaOrJianOrQiuhe")
	private Integer isJiaOrJianOrQiuhe;
    /**
     *  录入人
     */
	@TableField("staffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long staffID;
    /**
     * 录入时间
     */
	@TableField("lurudate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lurudate;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxsalarystyletable{" +
			", id=" + id +
			", salaryStyle=" + salaryStyle +
			", isJiaOrJianOrQiuhe=" + isJiaOrJianOrQiuhe +
			", staffID=" + staffID +
			", lurudate=" + lurudate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
