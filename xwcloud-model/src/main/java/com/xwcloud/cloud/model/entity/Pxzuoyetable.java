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
public class Pxzuoyetable extends Model<Pxzuoyetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 作业内容
     */
	@TableField("neirong")
	private String neirong;
    /**
     * 班级ID
     */
	@TableField("classID")
	private String classID;
    /**
     * 作业上交的截止时间
     */
	@TableField("endDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
    /**
     * 图片作业附件，多个文件逗号隔开
     */
	@TableField("zuoyeImg")
	private String zuoyeImg;
    /**
     * 音频作业附件，多个文件逗号隔开
     */
	@TableField("zuoyeMp3")
	private String zuoyeMp3;
    /**
     * 视频作业附件，多个文件逗号隔开
     */
	@TableField("zuoyeVideo")
	private String zuoyeVideo;
    /**
     * 其他作业附件，多个文件逗号隔开
     */
	@TableField("otherFile")
	private String otherFile;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;
	@TableField("addStaffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long addStaffID;
	@TableField("addTime")
	private Date addTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxzuoyetable{" +
			", id=" + id +
			", neirong=" + neirong +
			", classID=" + classID +
			", endDate=" + endDate +
			", zuoyeImg=" + zuoyeImg +
			", zuoyeMp3=" + zuoyeMp3 +
			", zuoyeVideo=" + zuoyeVideo +
			", otherFile=" + otherFile +
			", qiyeID=" + qiyeID +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			"}";
	}
}
