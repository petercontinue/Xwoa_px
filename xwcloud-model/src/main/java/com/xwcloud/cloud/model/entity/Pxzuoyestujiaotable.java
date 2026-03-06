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
public class Pxzuoyestujiaotable extends Model<Pxzuoyestujiaotable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private long stuID;
    /**
     * 作业ID
     */
	@TableField("zuoyeID")
	private long zuoyeID;
    /**
     *  交作业时间
     */
	@TableField("jiaozuoyeDateTime")
	private Date jiaozuoyeDateTime;
    /**
     * 备注
     */
	@TableField("beizhu")
	private String beizhu;
    /**
     * 作业图片，多个图片逗号隔开
     */
	@TableField("zuoyeImg")
	private String zuoyeImg;
    /**
     * 作业音频，多个文件逗号隔开
     */
	@TableField("zuoyeMp3")
	private String zuoyeMp3;
    /**
     * 作业视频，多个文件逗号隔开
     */
	@TableField("zuoyeVideo")
	private String zuoyeVideo;
    /**
     * 其他文件，多个文件逗号隔开
     */
	@TableField("otherFile")
	private String otherFile;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxzuoyestujiaotable{" +
			", id=" + id +
			", stuID=" + stuID +
			", zuoyeID=" + zuoyeID +
			", jiaozuoyeDateTime=" + jiaozuoyeDateTime +
			", beizhu=" + beizhu +
			", zuoyeImg=" + zuoyeImg +
			", zuoyeMp3=" + zuoyeMp3 +
			", zuoyeVideo=" + zuoyeVideo +
			", otherFile=" + otherFile +
			", qiyeID=" + qiyeID +
			"}";
	}
}
