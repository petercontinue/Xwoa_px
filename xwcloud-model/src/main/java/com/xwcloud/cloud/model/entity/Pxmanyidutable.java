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
public class Pxmanyidutable extends Model<Pxmanyidutable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("weixinmessageId")
	private String weixinmessageId;
    /**
     * 评价学员
     */
	@TableField("stuId")
	private String stuId;
    /**
     * 教师教学水平评价：1很不满意，2：比较差，3：还可以，4：满意，5：非常满意
     */
	@TableField("TeachingLevelOfTeachers")
	private String TeachingLevelOfTeachers;
    /**
     * 教师教学效果评价：1很不满意，2：比较差，3：还可以，4：满意，5：非常满意
     */
	@TableField("TeachingEffectOfTeachers")
	private String TeachingEffectOfTeachers;
    /**
     * 教师服务态度评价：1很不满意，2：比较差，3：还可以，4：满意，5：非常满意
     */
	@TableField("ServiceAttitude")
	private String ServiceAttitude;
    /**
     * 学校管理规范评价：1很不满意，2：比较差，3：还可以，4：满意，5：非常满意
     */
	@TableField("SchoolManagementNorms")
	private String SchoolManagementNorms;
    /**
     * 学校硬件环境评价：1很不满意，2：比较差，3：还可以，4：比较满意，5：非常满意
     */
	@TableField("SchoolFacilities")
	private String SchoolFacilities;
    /**
     * 评价日期
     */
	@TableField("pingjiaDate")
	private Date pingjiaDate;
    /**
     * 评价内容
     */
	@TableField("concent")
	private String concent;
	@TableField("qiyeID")
	private long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxmanyidutable{" +
			", id=" + id +
			", weixinmessageId=" + weixinmessageId +
			", stuId=" + stuId +
			", TeachingLevelOfTeachers=" + TeachingLevelOfTeachers +
			", TeachingEffectOfTeachers=" + TeachingEffectOfTeachers +
			", ServiceAttitude=" + ServiceAttitude +
			", SchoolManagementNorms=" + SchoolManagementNorms +
			", SchoolFacilities=" + SchoolFacilities +
			", pingjiaDate=" + pingjiaDate +
			", concent=" + concent +
			", qiyeID=" + qiyeID +
			"}";
	}
}
