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
 * @since 2021-05-21
 */
@Data
@Accessors(chain = true)
@TableName("wsc_goodsshuxinglistprice_pingtuan")
public class WscGoodsshuxinglistpricePingtuan extends Model<WscGoodsshuxinglistpricePingtuan> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 对应wsc_goodsShuxingListPrice表的id
     */
	@TableField("goodsShuxingListPriceID")
	private Long goodsShuxingListPriceID;
    /**
     * 拼团人数，即达到多少人是什么价
     */
	@TableField("pingtuanRenshu")
	private String pingtuanRenshu;
    /**
     * 拼团价格，即达到多少人是什么价
     */
	@TableField("pingtuanPrice")
	private BigDecimal pingtuanPrice;
	@TableField("shuoming")
	private String shuoming;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;

}
