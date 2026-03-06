package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
@Data
@Accessors(chain = true)
@TableName("oa_paymoneystyle")
public class OaPaymoneystyle extends Model<OaPaymoneystyle> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("paymoneyStyleName")
	private String paymoneyStyleName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaPaymoneystyle{" +
			", id=" + id +
			", paymoneyStyleName=" + paymoneyStyleName +
			"}";
	}
}
