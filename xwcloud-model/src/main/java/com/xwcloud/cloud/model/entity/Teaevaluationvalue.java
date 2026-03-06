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
 * @since 2021-08-02
 */
@Data
@Accessors(chain = true)
@TableName("teaevaluationvalue")
public class Teaevaluationvalue extends Model<Teaevaluationvalue> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 老师课后评价表ID
     */
	@TableField("pjid")
	private Long pjid;
    /**
     * 老师评价项ID
     */
	@TableField("rateid")
	private Long rateid;
    /**
     * 评分
     */
	@TableField("pfvalue")
	private BigDecimal pfvalue;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Teaevaluationvalue{" +
			", id=" + id +
			", pjid=" + pjid +
			", rateid=" + rateid +
			", pfvalue=" + pfvalue +
			", qiyeID=" + qiyeID +
			"}";
	}
}
