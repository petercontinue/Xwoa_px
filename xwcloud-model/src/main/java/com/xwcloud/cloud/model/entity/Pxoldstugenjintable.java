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
public class Pxoldstugenjintable extends Model<Pxoldstugenjintable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 老学员ID
     */
	@TableField("stuID")
	private String stuID;
    /**
     * 跟进内容
     */
	@TableField("genjinContent")
	private String genjinContent;
    /**
     * 跟进时间
     */
	@TableField("genjintime")
	private Date genjintime;
	@TableField("adduser")
	private String adduser;
	@TableField("addtime")
	private Date addtime;
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxoldstugenjintable{" +
			", id=" + id +
			", stuID=" + stuID +
			", genjinContent=" + genjinContent +
			", genjintime=" + genjintime +
			", adduser=" + adduser +
			", addtime=" + addtime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
