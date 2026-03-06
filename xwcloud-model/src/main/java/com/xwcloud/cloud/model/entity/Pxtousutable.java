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
 * @since 2020-11-04
 */
@Data
@Accessors(chain = true)
public class Pxtousutable extends Model<Pxtousutable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("openid")
	private String openid;
	@TableField("stuID")
	private String stuID;
    /**
     * 投诉内容
     */
	@TableField("tousuContent")
	private String tousuContent;
    /**
     * 投诉时间
     */
	@TableField("tousuDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date tousuDate;
    /**
     * 查阅状态 true:已查阅   false:未查阅
     */
	@TableField("chayueState")
	private Boolean chayueState;
    /**
     * 查阅日期
     */
	@TableField("chayueDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date chayueDate;
    /**
     * 查阅人
     */
	@TableField("chayueSatff")
	private String chayueSatff;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxtousutable{" +
			", id=" + id +
			", openid=" + openid +
			", stuID=" + stuID +
			", tousuContent=" + tousuContent +
			", tousuDate=" + tousuDate +
			", chayueState=" + chayueState +
			", chayueDate=" + chayueDate +
			", chayueSatff=" + chayueSatff +
			", qiyeID=" + qiyeID +
			"}";
	}
}
