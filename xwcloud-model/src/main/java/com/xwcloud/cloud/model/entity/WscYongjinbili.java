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
 * <p>a
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Data
@Accessors(chain = true)
@TableName("wsc_yongjinbili")
public class WscYongjinbili extends Model<WscYongjinbili> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 级别，是父级，还祖父级
     */
	@TableField("jibei")
	private Integer jibei;
    /**
     * 返拥比例，如果商品设置了返佣比例，则该比例无效
     */
	@TableField("bili")
	private BigDecimal bili;
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
		return "WscYongjinbili{" +
			", id=" + id +
			", jibei=" + jibei +
			", bili=" + bili +
			", qiyeid=" + qiyeID +
			"}";
	}
}
