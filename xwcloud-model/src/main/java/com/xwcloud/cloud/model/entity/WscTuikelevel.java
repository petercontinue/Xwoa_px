package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-19
 */
@Data
@Accessors(chain = true)
@TableName("wsc_tuikeLevel")
public class WscTuikelevel extends Model<WscTuikelevel> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 推客级别
     */
	@TableField("tuikeLevelName")
	private String tuikeLevelName;
    /**
     * 条件金额
     */
	@TableField("tiaojianMoney")
	private BigDecimal tiaojianMoney;
    /**
     * 父级返佣比（团队成员购买）
     */
	@TableField("fjFanyongbi1")
	private BigDecimal fjFanyongbi1;
    /**
     * 祖级返佣比（团队成员购买）
     */
	@TableField("zjFanyongbi1")
	private BigDecimal zjFanyongbi1;
    /**
     * 父级返佣比（团队成员成为推客）
     */
	@TableField("fjFanyongbi2")
	private BigDecimal fjFanyongbi2;
    /**
     * 祖级返佣比（团队成员成为推客）
     */
	@TableField("zjFanyongbi2")
	private BigDecimal zjFanyongbi2;
    /**
     * 是否显示：1显示；0：不显示；默认值为1
     */
	@TableField("isShow")
	private Integer isShow;
	@TableField("shuoming")
	private String shuoming;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscTuikelevel{" +
			", id=" + id +
			", tuikeLevelName=" + tuikeLevelName +
			", tiaojianMoney=" + tiaojianMoney +
			", fjFanyongbi1=" + fjFanyongbi1 +
			", zjFanyongbi1=" + zjFanyongbi1 +
			", fjFanyongbi2=" + fjFanyongbi2 +
			", zjFanyongbi2=" + zjFanyongbi2 +
			", isShow=" + isShow +
			", shuoming=" + shuoming +
			", qiyeID=" + qiyeID +
			"}";
	}
}
