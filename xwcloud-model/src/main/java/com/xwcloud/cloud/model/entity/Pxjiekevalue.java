package com.xwcloud.cloud.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-27
 */
@Data
@Accessors(chain = true)
@TableName("pxjiekevalue")
public class Pxjiekevalue extends Model<Pxjiekevalue> {

    private static final long serialVersionUID = 1L;

    /**
     * 结课信息表ID
     */
    @TableId("id")
	private Long id;
    /**
     * 关联结课表ID 
     */
	@TableField("jiekeID")
	private Long jiekeID;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 补习课程ID 结课时学员有几个补习课程加几条记录
     */
	@TableField("buxiID")
	private Long buxiID;
    /**
     * 结课前对应补习课程ID 的剩余课时
     */
	@TableField("remainkeshi")
	private BigDecimal remainkeshi;
    /**
     * 课程ID
     */
	@TableField("kechengID")
	private Long kechengID;
    /**
     * 课程单价
     */
	@TableField("kechengPrice")
	private BigDecimal kechengPrice;
    /**
     * 添加时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addTime")
	private Date addTime;
    /**
     * 添加人
     */
	@TableField("addUser")
	private Long addUser;
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
		return "Pxjiekevalue{" +
			", id=" + id +
			", jiekeID=" + jiekeID +
			", stuID=" + stuID +
			", buxiID=" + buxiID +
			", remainkeshi=" + remainkeshi +
			", kechengID=" + kechengID +
			", kechengPrice=" + kechengPrice +
			", addTime=" + addTime +
			", addUser=" + addUser +
			", qiyeID=" + qiyeID +
			"}";
	}
}
