package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-04-08
 */
@Data
@Accessors(chain = true)
@TableName("tuifeishenpi")
public class Tuifeishenpi extends Model<Tuifeishenpi> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 退费时选择的签单
     */
	@TableField("tuiqiandanID")
	private String tuiqiandanID;
    /**
     * 退费校区（有签单时为签单的校区，没有时，为学员的校区）
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 退费时 要审批的签单ID
     */
	@TableField("spqiandanID")
	private String spqiandanID;
    /**
     * 要给那个学员退费
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 退费类型：1全科全退，2单科全退，3 单科部分退费，4退杂费，5退教学用品的费用，6退充值余额
     */
	@TableField("type")
	private Integer type;
    /**
     * 退费的金额
     */
	@TableField("yingtuiMoney")
	private BigDecimal yingtuiMoney;
    /**
     * 申请退费金额
     */
	@TableField("sqtuiMoney")
	private BigDecimal sqtuiMoney;
    /**
     * 优惠金额（没有为0）
     */
	@TableField("youhuiMoney")
	private BigDecimal youhuiMoney;
    /**
     * 代金券金额（没有为0）
     */
	@TableField("daijinquanMoney")
	private BigDecimal daijinquanMoney;
    /**
     * 退费办理时间（保存退费信息时记录的流水时间）
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("tuifeibanlidate")
	private Date tuifeibanlidate;
    /**
     * 退费说明
     */
    @TableField("tuifeishuoming")
	private String tuifeishuoming;
    /**
     * 退费的支付方式
     */
	@TableField("sppayMoneystyle")
	private Long sppayMoneystyle;
    /**
     * 审批时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("chuliTime")
	private Date chuliTime;
    /**
     * 退费业绩人
     */
	@TableField("yejiren")
	private Long yejiren;
    /**
     * 审批人
     */
	@TableField("spUser")
	private Long spUser;
    /**
     * 1：待审批  2审批通过  3审批不通过
     */
	@TableField("spfinish")
	private Integer spfinish;
    /**
     * 审批说明
     */
	@TableField("spshuoming")
	private String spshuoming;
    /**
     * 申请人
     */
	@TableField("adduser")
	private Long adduser;
    /**
     * 发起时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addTiem")
	private Date addTiem;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.tuifeishuoming;
	}

	@Override
	public String toString() {
		return "Tuifeishenpi{" +
			", id=" + id +
			", tuiqiandanID=" + tuiqiandanID +
			", campusID=" + campusID +
			", spqiandanID=" + spqiandanID +
			", stuID=" + stuID +
			", type=" + type +
			", yingtuiMoney=" + yingtuiMoney +
			", sqtuiMoney=" + sqtuiMoney +
			", youhuiMoney=" + youhuiMoney +
			", daijinquanMoney=" + daijinquanMoney +
			", tuifeibanlidate=" + tuifeibanlidate +
			", tuifeishuoming=" + tuifeishuoming +
			", sppayMoneystyle=" + sppayMoneystyle +
			", chuliTime=" + chuliTime +
			", yejiren=" + yejiren +
			", spUser=" + spUser +
			", spfinish=" + spfinish +
			", spshuoming=" + spshuoming +
			", adduser=" + adduser +
			", addTiem=" + addTiem +
			", qiyeID=" + qiyeID +
			"}";
	}
}
