package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>a
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Data
@Accessors(chain = true)
@TableName("whd_chongzhi_record")
public class WhdChongzhiRecord extends Model<WhdChongzhiRecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 微商城用户ID
     */
	@TableField("wscUserID")
	private Long wscuserid;
    /**
     * 充值活动ID
     */
	@TableField("czHuodongID")
	private Long czhuodongid;
    /**
     * 充值金额
     */
	@TableField("chongzhiMoney")
	private BigDecimal chongzhimoney;
    /**
     * 赠送金额
     */
	@TableField("songMoney")
	private BigDecimal songmoney;
    /**
     * 实得金额
     */
	@TableField("shideMoney")
	private BigDecimal shidemoney;
    /**
     * 充值说明
     */
	@TableField("chongzhiShuoming")
	private String chongzhishuoming;
    /**
     * 充值时间
     */
	@TableField("chongzhiTime")
	private LocalDateTime chongzhitime;
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
		return "WhdChongzhiRecord{" +
			", id=" + id +
			", wscuserid=" + wscuserid +
			", czhuodongid=" + czhuodongid +
			", chongzhimoney=" + chongzhimoney +
			", songmoney=" + songmoney +
			", shidemoney=" + shidemoney +
			", chongzhishuoming=" + chongzhishuoming +
			", chongzhitime=" + chongzhitime +
			", qiyeid=" + qiyeID +
			"}";
	}
}
