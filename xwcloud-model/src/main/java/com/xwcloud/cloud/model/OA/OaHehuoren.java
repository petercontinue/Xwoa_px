package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
@Data
@Accessors(chain = true)
@TableName("oa_hehuoren")
public class OaHehuoren extends Model<OaHehuoren> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 合伙人真实姓名
     */
	@TableField("realName")
	private String realName;
    /**
     * 企业ID，-1表示是不属于任何一个机构，默认值-1
     */
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     *  性别
     */
	@TableField("sex")
	private String sex;
    /**
     * 手机号
     */
	@TableField("phone")
	private String phone;
    /**
     *  省ID
     */
	@TableField("prinvinceID")
	private Long prinvinceID;
    /**
     *  城市ID
     */
	@TableField("cityID")
	private Long cityID;
    /**
     * 合伙人级别ID，注意1是意向合伙人，即还没有成交，默认值1
     */
	@TableField("hehuoLevel")
	private Long hehuoLevel;
    /**
     * 当前合伙人级别的开始日期
     */
	@TableField("levelStartTime")
	private Date levelStartTime;
    /**
     * 当前合伙人级别的结束日期
     */
	@TableField("levelEndTime")
	private Date levelEndTime;
    /**
     * 返佣比浮动，正数表示加，负数表示减，默认值0
     */
	@TableField("fanyongbiFloat")
	private BigDecimal fanyongbiFloat;
    /**
     * 合伙人说明
     */
	@TableField("shuoming")
	private String shuoming;
    /**
     * 首次签单时间
     */
	@TableField("firstQiandanTime")
	private Date firstQiandanTime;
    /**
     * A类合伙人：买系统成为合伙人；B类合伙人：转介绍成为合伙人，C类意向合伙人：即还没有成交的,默认值：C类意向合伙人
     */
	@TableField("hehuoType")
	private String hehuoType;
    /**
     * 合伙人来源，0后台OA添加的，1网上自己注册进来的
     */
	@TableField("hhFrom")
	private Integer hhFrom;
    /**
     * 合伙人身份：1机构负责人，2机构股东，3机构行政人员，4机构老师，5机构其他人员，6学员，7学员家长，8其他
     */
	@TableField("hhrRole")
	private Integer hhrRole;
    /**
     *  
     */
	@TableField("addUser")
	private Long addUser;
	@TableField("addTime")
	private Date addTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaHehuoren{" +
			", id=" + id +
			", realName=" + realName +
			", qiyeID=" + qiyeID +
			", sex=" + sex +
			", phone=" + phone +
			", prinvinceID=" + prinvinceID +
			", cityID=" + cityID +
			", hehuoLevel=" + hehuoLevel +
			", levelStartTime=" + levelStartTime +
			", levelEndTime=" + levelEndTime +
			", fanyongbiFloat=" + fanyongbiFloat +
			", shuoming=" + shuoming +
			", firstQiandanTime=" + firstQiandanTime +
			", hehuoType=" + hehuoType +
			", hhFrom=" + hhFrom +
			", hhrRole=" + hhrRole +
			", addUser=" + addUser +
			", addTime=" + addTime +
			"}";
	}
}
