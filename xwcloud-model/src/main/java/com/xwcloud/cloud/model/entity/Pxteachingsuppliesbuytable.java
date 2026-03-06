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
 * @since 2020-11-23
 */
@Data
@Accessors(chain = true)
public class Pxteachingsuppliesbuytable extends Model<Pxteachingsuppliesbuytable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("campusID")
	private Long campusID;
    /**
     * 要采购的商品名称
     */
	@TableField("shangpingName")
	private String shangpingName;
    /**
     * 商品类别ID
     */
	@TableField("shangpingTypeID")
	private Long shangpingTypeID;
    /**
     * 规格
     */
	@TableField("guigeID")
	private String guigeID;
    /**
     * 购买数量
     */
	@TableField("buyNum")
	private BigDecimal buyNum;
    /**
     * 单位
     */
	@TableField("danwei")
	private String danwei;
	@TableField("addStaffID")
	private Long addStaffID;
    /**
     * 备注
     */
	@TableField("beizhu")
	private String beizhu;
	@TableField("addDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addDate;
    /**
     * 审核状态，0未审核，1已审核通过，2已审核不通过
     */
	@TableField("isShenhe")
	private Integer isShenhe;
    /**
     * 审核未通过原因
     */
	@TableField("shenheNopassReason")
	private String shenheNopassReason;
	@TableField("qiyeID")
	private Long qiyeID;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxteachingsuppliesbuytable{" +
			", id=" + id +
			", campusID=" + campusID +
			", shangpingName=" + shangpingName +
			", shangpingTypeID=" + shangpingTypeID +
			", guigeID=" + guigeID +
			", buyNum=" + buyNum +
			", danwei=" + danwei +
			", addStaffID=" + addStaffID +
			", beizhu=" + beizhu +
			", addDate=" + addDate +
			", isShenhe=" + isShenhe +
			", shenheNopassReason=" + shenheNopassReason +
			", qiyeID=" + qiyeID +
			"}";
	}
}
