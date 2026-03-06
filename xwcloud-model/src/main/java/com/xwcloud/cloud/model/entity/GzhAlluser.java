package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-08-11
 */
@Data
@Accessors(chain = true)
@TableName("gzh_AllUser")
public class GzhAlluser extends Model<GzhAlluser> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("openid")
	private String openid;
	@TableField("nickname")
	private String nickname;
	@TableField("subscribe_time")
	private String subscribeTime;
	@TableField("unionid")
	private String unionid;
	@TableField("subscribe")
	private String subscribe;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "GzhAlluser{" +
			", id=" + id +
			", openid=" + openid +
			", nickname=" + nickname +
			", subscribeTime=" + subscribeTime +
			", unionid=" + unionid +
			", subscribe=" + subscribe +
			"}";
	}
}
