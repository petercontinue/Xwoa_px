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
 * @since 2021-07-03
 */
@Data
@Accessors(chain = true)
@TableName("oa_sms_sendrecords")
public class OaSmsSendrecords extends Model<OaSmsSendrecords> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 短信内容
     */
	@TableField("smscontent")
	private String smscontent;
    /**
     * 短信发送时间
     */
	@TableField("sendTime")
	private Date sendTime;
    /**
     * 短信发送手机号码
     */
	@TableField("smsPhone")
	private String smsPhone;
    /**
     * 短信发送说明
     */
	@TableField("shuoming")
	private String shuoming;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaSmsSendrecords{" +
			", id=" + id +
			", qiyeID=" + qiyeID +
			", smscontent=" + smscontent +
			", sendTime=" + sendTime +
			", smsPhone=" + smsPhone +
			", shuoming=" + shuoming +
			"}";
	}
}
