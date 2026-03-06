package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@TableName("oa_product")
public class OaProduct extends Model<OaProduct> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 产品名称
     */
	@TableField("productName")
	private String productName;
    /**
     *  产品说明
     */
	@TableField("shuoming")
	private String shuoming;
	@TableField("addUser")
	private Long addUser;
	@TableField("addTime")
	private Date addTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaProduct{" +
			", id=" + id +
			", productName=" + productName +
			", shuoming=" + shuoming +
			", addUser=" + addUser +
			", addTime=" + addTime +
			"}";
	}
}
