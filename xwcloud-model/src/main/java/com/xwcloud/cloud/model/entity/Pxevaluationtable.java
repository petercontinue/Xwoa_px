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
public class Pxevaluationtable extends Model<Pxevaluationtable> {

    private static final long serialVersionUID = 1L;


    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 评价文字
     */
	@TableField("note")
	private String note;
	@TableField("kechengID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long kechengID;
	@TableField("classID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long classID;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("haveClassDate")
	private Date haveClassDate;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@TableField("startLessonDateTime")
	private Date startLessonDateTime;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@TableField("endLessonDateTime")
	private Date endLessonDateTime;

	@TableField("weekN")
	private String weekN;
    /**
     * 教师课时表ID
     */
	@TableField("keshiTeachTabID")
	private Long keshiTeachTabID;
	@TableField("stuid")
	private Long stuid;
	@TableField("teacherid")
	private Long teacherid;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("addtime")
	private Date addtime;
    /**
     * 评价图片
     */
	@TableField("images")
	private String images;
    /**
     * 评价音频url
     */
	@TableField("pjmp3Url")
	private String pjmp3Url;
    /**
     * 评价视频url
     */
	@TableField("pjvideoUrl")
	private String pjvideoUrl;
    /**
     * NULL 1教师评价学生。2学生评价老师
     */
	@TableField("type")
	private Integer type;
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxevaluationtable{" +
			", id=" + id +
			", note=" + note +
			", kechengID=" + kechengID +
			", classID=" + classID +
			", haveClassDate=" + haveClassDate +
			", startLessonDateTime=" + startLessonDateTime +
			", endLessonDateTime=" + endLessonDateTime +
			", weekN=" + weekN +
			", keshiTeachTabID=" + keshiTeachTabID +
			", stuid=" + stuid +
			", teacherid=" + teacherid +
			", addtime=" + addtime +
			", images=" + images +
			", pjmp3Url=" + pjmp3Url +
			", pjvideoUrl=" + pjvideoUrl +
			", type=" + type +
			", qiyeID=" + qiyeID +
			"}";
	}
}
