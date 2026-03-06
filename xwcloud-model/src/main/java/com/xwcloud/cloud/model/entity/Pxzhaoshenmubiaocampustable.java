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
 * @since 2020-11-26
 */
@Data
@Accessors(chain = true)
@TableName("pxzhaoshenmubiaocampustable")
public class Pxzhaoshenmubiaocampustable extends Model<Pxzhaoshenmubiaocampustable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 校区ID
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 年月
     */
	@TableField("yearMonth")
	private String yearMonth;
    /**
     * 月业绩目标
     */
	@TableField("monthMoney")
	private BigDecimal monthMoney;
    /**
     * 月招生人数目标
     */
	@TableField("monthSum")
	private Integer monthSum;
    /**
     * 添加人
     */
	@TableField("addStaffID")
	private Long addStaffID;
    /**
     * 添加时间
     */
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxzhaoshenmubiaocampustable{" +
			", id=" + id +
			", campusID=" + campusID +
			", yearMonth=" + yearMonth +
			", monthMoney=" + monthMoney +
			", monthSum=" + monthSum +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
