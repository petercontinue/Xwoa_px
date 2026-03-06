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
 * @since 2021-06-29
 */
@Data
@Accessors(chain = true)
@TableName("oa_staffpost")
public class OaStaffpost extends Model<OaStaffpost> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("staffpostName")
	private String staffpostName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaStaffpost{" +
			", id=" + id +
			", staffpostName=" + staffpostName +
			"}";
	}
}
