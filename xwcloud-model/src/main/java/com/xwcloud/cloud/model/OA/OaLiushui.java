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
 * @since 2021-06-29
 */
@Data
@Accessors(chain = true)
@TableName("oa_liushui")
public class OaLiushui extends Model<OaLiushui> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水ID
     */
    @TableId("id")
	private Long id;
    /**
     * 经办人员工ID
     */
	@TableField("jinbanrenStaffID")
	private Long jinbanrenStaffID;
    /**
     * 流水时间
     */
	@TableField("liushuiDatetime")
	private Date liushuiDatetime;
    /**
     * 流水说明
     */
	@TableField("liushuishuoming")
	private String liushuishuoming;
    /**
     * 录入时间
     */
	@TableField("lurutime")
	private Date lurutime;
    /**
     * 支付方式
     */
	@TableField("paymoneystyleID")
	private Long paymoneystyleID;
    /**
     * 签单ID
     */
	@TableField("qiandanID")
	private Long qiandanID;
    /**
     * 收入金额
     */
	@TableField("shourumoney")
	private BigDecimal shourumoney;
    /**
     * 支出金额
     */
	@TableField("zhichumoney")
	private BigDecimal zhichumoney;

	@TableField("liushuiStyleID")
	private Long liushuiStyleID;

	@TableField("isShouruOrZhichu")
	private Integer isShouruOrZhichu;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaLiushui{" +
			", id=" + id +
			", jinbanrenStaffID=" + jinbanrenStaffID +
			", liushuiDatetime=" + liushuiDatetime +
			", liushuishuoming=" + liushuishuoming +
			", lurutime=" + lurutime +
			", paymoneystyleID=" + paymoneystyleID +
			", qiandanID=" + qiandanID +
			", shourumoney=" + shourumoney +
			", zhichumoney=" + zhichumoney +
			"}";
	}
}
