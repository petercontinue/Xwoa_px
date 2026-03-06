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
 * @since 2020-10-24
 */
@Data
@Accessors(chain = true)
public class Pxshouzhistyletable extends Model<Pxshouzhistyletable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 收支项目名称
     */
	@TableField("shouzhiStyle")
	private String shouzhiStyle;
    /**
     * 是收入还是支出，1收入；2支出
     */
	@TableField("isshouOrzhichu")
	private String isshouOrzhichu;
    /**
     * 备注
     */
	@TableField("beizhu")
	private String beizhu;
    /**
     * 添加人
     */
	@TableField("staffID")
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
		return "Pxshouzhistyletable{" +
			", id=" + id +
			", shouzhiStyle=" + shouzhiStyle +
			", isshouOrzhichu=" + isshouOrzhichu +
			", beizhu=" + beizhu +
			", staffID=" + staffID +
			", lurudate=" + lurudate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
