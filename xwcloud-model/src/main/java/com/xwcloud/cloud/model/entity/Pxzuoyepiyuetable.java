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
public class Pxzuoyepiyuetable extends Model<Pxzuoyepiyuetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 学生ID
     */
	@TableField("stuID")
	private String stuID;
    /**
     * 学生交作业ID
     */
	@TableField("zuoyeStujiaoID")
	private String zuoyeStujiaoID;
    /**
     * 批阅老师ID
     */
	@TableField("piyueTeacherID")
	private String piyueTeacherID;
    /**
     * 批阅文本内容
     */
	@TableField("piyueContent")
	private String piyueContent;
    /**
     * 批阅时间
     */
	@TableField("piyueDatetime")
	private Date piyueDatetime;
    /**
     * 作业批阅图片，多个文件逗号隔开
     */
	@TableField("piyueImg")
	private String piyueImg;
    /**
     * 作业批阅音频，多个文件逗号隔开
     */
	@TableField("piyueMp3")
	private String piyueMp3;
    /**
     * 作业批阅视频，多个文件逗号隔开
     */
	@TableField("piyueVedio")
	private String piyueVedio;
    /**
     * 作业批阅其他文件，多个文件逗号隔开
     */
	@TableField("piyueOtherFile")
	private String piyueOtherFile;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxzuoyepiyuetable{" +
			", id=" + id +
			", stuID=" + stuID +
			", zuoyeStujiaoID=" + zuoyeStujiaoID +
			", piyueTeacherID=" + piyueTeacherID +
			", piyueContent=" + piyueContent +
			", piyueDatetime=" + piyueDatetime +
			", piyueImg=" + piyueImg +
			", piyueMp3=" + piyueMp3 +
			", piyueVedio=" + piyueVedio +
			", piyueOtherFile=" + piyueOtherFile +
			", qiyeID=" + qiyeID +
			"}";
	}
}
