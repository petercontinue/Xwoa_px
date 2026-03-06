package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Data
@Accessors(chain = true)
public class Pxtuifeikechengtable extends Model<Pxtuifeikechengtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 退费补习课程ID
     */
	@TableField("tfBuxiID")
	private Long tfBuxiID;
    /**
     * 退费课程ID
     */
	@TableField("tfKechengID")
	private Long tfKechengID;
    /**
     * 课程单价
     */
	@TableField("kechengprice")
	private BigDecimal kechengprice;
    /**
     * 退费前的剩余课时
     */
	@TableField("tfqianRemainkeshi")
	private BigDecimal tfqianRemainkeshi;
    /**
     * 退了多少课时
     */
	@TableField("tfkeshi")
	private BigDecimal tfkeshi;
    /**
     * 退费后的剩余课时
     */
	@TableField("tfhouRemainkeshi")
	private BigDecimal tfhouRemainkeshi;
    /**
     * 单科备注
     */
	@TableField("beizhu")
	private String beizhu;
    /**
     * 退费表ID
     */
	@TableField("tuifeiID")
	private Long tuifeiID;
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
		return "Pxtuifeikechengtable{" +
			", id=" + id +
			", tfBuxiID=" + tfBuxiID +
			", tfKechengID=" + tfKechengID +
			", kechengprice=" + kechengprice +
			", tfqianRemainkeshi=" + tfqianRemainkeshi +
			", tfkeshi=" + tfkeshi +
			", tfhouRemainkeshi=" + tfhouRemainkeshi +
			", beizhu=" + beizhu +
			", tuifeiID=" + tuifeiID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
