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
public class Pxyuekestufaqistujointable extends Model<Pxyuekestufaqistujointable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 关联pxyuekestufaqitable的id
     */
	@TableField("yuekeStufaqiID")
	private String yuekeStufaqiID;
    /**
     * 响应参加的学员ID
     */
	@TableField("joinStuID")
	private String joinStuID;
    /**
     * 响应学员的补习课程ID
     */
	@TableField("buxikechengID")
	private String buxikechengID;
    /**
     * 响应参加的时间
     */
	@TableField("addTime")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxyuekestufaqistujointable{" +
			", id=" + id +
			", yuekeStufaqiID=" + yuekeStufaqiID +
			", joinStuID=" + joinStuID +
			", buxikechengID=" + buxikechengID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
