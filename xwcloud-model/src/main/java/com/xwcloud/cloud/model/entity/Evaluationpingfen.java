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
@TableName("evaluationpingfen")
public class Evaluationpingfen extends Model<Evaluationpingfen> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 评分项
     */
	@TableField("pfName")
	private String pfName;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Evaluationpingfen{" +
			", id=" + id +
			", pfName=" + pfName +
			", qiyeID=" + qiyeID +
			"}";
	}
}
