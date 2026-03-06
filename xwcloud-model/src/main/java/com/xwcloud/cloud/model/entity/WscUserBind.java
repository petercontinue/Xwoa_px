package com.xwcloud.cloud.model.entity;

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
 * @since 2021-08-10
 */
@Data
@Accessors(chain = true)
@TableName("wsc_user_bind")
public class WscUserBind extends Model<WscUserBind> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 微商城用户ID
     */
	@TableField("wscuserid")
	private Long wscuserid;
    /**
     * 学员ID
     */
	@TableField("stuId")
	private Long stuId;
    /**
     * 绑定手机号码
     */
	@TableField("phoneNumber")
	private String phoneNumber;
    /**
     * 家长电话关系：1本人，2爸爸，3妈妈，4爷爷，5奶奶，6外公，7外婆，8保姆，9其他
     */
	@TableField("parentTelRelation")
	private String parentTelRelation;
    /**
     * 0:主账号；1子账号
     */
	@TableField("role")
	private Integer role;
    /**
     * 是否允许登录培训系统，0不允许，1允许，默认1
     */
	@TableField("isCanLoginPxsys")
	private Integer isCanLoginPxsys;
    /**
     * 是否接收培训微信消息，0不接收，1接收，默认1
     */
	@TableField("isReceivePxmsg")
	private Integer isReceivePxmsg;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscUserBind{" +
			", id=" + id +
			", wscuserid=" + wscuserid +
			", stuId=" + stuId +
			", phoneNumber=" + phoneNumber +
			", parentTelRelation=" + parentTelRelation +
			", role=" + role +
			", isCanLoginPxsys=" + isCanLoginPxsys +
			", isReceivePxmsg=" + isReceivePxmsg +
			", qiyeID=" + qiyeID +
			"}";
	}
}
