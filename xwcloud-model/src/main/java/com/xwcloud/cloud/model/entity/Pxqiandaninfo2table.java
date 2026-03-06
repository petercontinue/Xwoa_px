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
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
public class Pxqiandaninfo2table extends Model<Pxqiandaninfo2table> {

    private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId("id")
	private Long id;
	@TableField("qianDanOtherMoneyID")
	private Long qianDanOtherMoneyID;
    /**
     * 教育用品ID
     */
	@TableField("jiaoxueYonpingID")
	private Long jiaoxueYonpingID;
    /**
     * 单价
     */
	@TableField("onePrice")
	private BigDecimal onePrice;
    /**
     * 购买数量
     */
	@TableField("nums")
	private BigDecimal nums;
    /**
     * 总价
     */
	@TableField("zongMoney")
	private BigDecimal zongMoney;
    /**
     * 签单ID
     */
	@TableField("qianInfoTabID")
	private Long qianInfoTabID;
    /**
     * 1签单杂费，2教学用品购买交费
     */
	@TableField("type")
	private Integer type;
	@TableField("tuiMoney")
	private BigDecimal tuiMoney;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxqiandaninfo2table{" +
			", id=" + id +
			", qianDanOtherMoneyID=" + qianDanOtherMoneyID +
			", jiaoxueYonpingID=" + jiaoxueYonpingID +
			", onePrice=" + onePrice +
			", nums=" + nums +
			", zongMoney=" + zongMoney +
			", qianInfoTabID=" + qianInfoTabID +
			", type=" + type +
			", tuiMoney=" + tuiMoney +
			", qiyeID=" + qiyeID +
			"}";
	}
}
