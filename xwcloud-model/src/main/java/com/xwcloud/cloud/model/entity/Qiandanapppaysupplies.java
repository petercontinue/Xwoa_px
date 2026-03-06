package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Data
@Accessors(chain = true)
@TableName("qiandanapppaysupplies")
public class Qiandanapppaysupplies extends Model<Qiandanapppaysupplies> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 签单ApppayID
     */
	@TableField("qiandanAppayID")
	private Long qiandanAppayID;
    /**
     * 教学用品ID
     */
	@TableField("TeachingSuppliesID")
	private Long teachingSuppliesID;
    /**
     * 名称
     */
	@TableField("Name")
	private String name;
    /**
     * 单价
     */
	@TableField("BuyPrice")
	private BigDecimal buyPrice;
    /**
     * 购买数量
     */
	@TableField("BuySum")
	private BigDecimal buySum;
    /**
     * 总金额
     */
	@TableField("SumMoney")
	private BigDecimal sumMoney;
    /**
     * 1:已发放；2：未发放;默认1
     */
	@TableField("fafangstate")
	private Integer fafangstate;
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
		return "Qiandanapppaysupplies{" +
			", id=" + id +
			", qiandanAppayID=" + qiandanAppayID +
			", teachingSuppliesID=" + teachingSuppliesID +
			", name=" + name +
			", buyPrice=" + buyPrice +
			", buySum=" + buySum +
			", sumMoney=" + sumMoney +
			", fafangstate=" + fafangstate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
