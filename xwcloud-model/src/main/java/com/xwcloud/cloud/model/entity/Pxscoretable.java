package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
public class Pxscoretable extends Model<Pxscoretable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 科目ID
     */
	@TableField("subjectID")
	private Long subjectID;
    /**
     * 课程ID
     */
	@TableField("kechengID")
	private Long kechengID;
    /**
     * 成绩分灵敏
     */
	@TableField("score")
	private BigDecimal score;
    /**
     * 单科排名
     */
	@TableField("dankepaiming")
	private int dankepaiming;
    /**
     * 总分排名
     */
	@TableField("zongfenpaiming")
	private int zongfenpaiming;
    /**
     * 0：培训机构成绩，1为在校成绩；
     */
	@TableField("scoreType")
	private Integer scoreType;
    /**
     * 考试类别：周考；月考；期中考；期末考等客户自定义
     */
	@TableField("testTypeID")
	private Long testTypeID;
    /**
     * 考试主题
     */
	@TableField("testTitle")
	private String testTitle;
    /**
     * 录入时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addDateTime")
	private Date addDateTime;
    /**
     * 录入人
     */
	@TableField("addStaffID")
	private Long addStaffID;
    /**
     * 备注
     */
	@TableField("beiZhu")
	private String beiZhu;
    /**
     * 考试时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("scoreDate")
	private Date scoreDate;
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
		return "Pxscoretable{" +
			", id=" + id +
			", stuID=" + stuID +
			", subjectID=" + subjectID +
			", kechengID=" + kechengID +
			", score=" + score +
			", dankepaiming=" + dankepaiming +
			", zongfenpaiming=" + zongfenpaiming +
			", scoreType=" + scoreType +
			", testTypeID=" + testTypeID +
			", testTitle=" + testTitle +
			", addDateTime=" + addDateTime +
			", addStaffID=" + addStaffID +
			", beiZhu=" + beiZhu +
			", scoreDate=" + scoreDate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
