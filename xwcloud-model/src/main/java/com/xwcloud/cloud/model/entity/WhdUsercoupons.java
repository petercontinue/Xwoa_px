package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-05-25
 */
@Data
@Accessors(chain = true)
@TableName("whd_usercoupons")
public class WhdUsercoupons extends Model<WhdUsercoupons> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 优惠券ID
     */
	@TableField("couponsID")
	private Long couponsID;
    /**
     * 1：赠送得到  2满赠的
     */
	@TableField("type")
	private Integer type;
    /**
     * 商城用户ID
     */
	@TableField("userID")
	private Long userID;
    /**
     * 赠送原因
     */
	@TableField("givashouming")
	private String givashouming;
    /**
     * true : 已使用 false 未使用
     */
	@TableField("isUse")
	private Boolean isUse;
    /**
     * 使用时间
     */
	@TableField("useDate")
	private Date useDate;
	@TableField("addDate")
	private Date addDate;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdUsercoupons{" +
			", id=" + id +
			", couponsID=" + couponsID +
			", type=" + type +
			", userID=" + userID +
			", givashouming=" + givashouming +
			", isUse=" + isUse +
			", useDate=" + useDate +
			", addDate=" + addDate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
