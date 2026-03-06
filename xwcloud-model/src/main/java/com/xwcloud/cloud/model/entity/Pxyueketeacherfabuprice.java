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
 * @since 2021-05-17
 */
@Data
@Accessors(chain = true)
@TableName("pxyueketeacherfabuprice")
public class Pxyueketeacherfabuprice extends Model<Pxyueketeacherfabuprice> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 约课老师发布信息ID
     */
	@TableField("yuekeID")
	private Long yuekeID;
    /**
     * 加入约课的学生人数
     */
	@TableField("yuekerenshu")
	private Integer yuekerenshu;
    /**
     * 加入约课学生人数对应的价格
     */
	@TableField("yuekeprice")
	private BigDecimal yuekeprice;
    /**
     * qiyeID
     */
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxyueketeacherfabuprice{" +
			", id=" + id +
			", yuekeID=" + yuekeID +
			", yuekerenshu=" + yuekerenshu +
			", yuekeprice=" + yuekeprice +
			", qiyeID=" + qiyeID +
			"}";
	}
}
