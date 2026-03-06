package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@TableName("whd_jizan_helpjizan")
public class WhdJizanHelpjizan extends Model<WhdJizanHelpjizan> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 发起积赞的id,对应whd_jizan_faqimyjizan表的id
     */
	@TableField("whd_jizan_faqimyjizan_id")
	private Long whdJizanFaqimyjizanId;
    /**
     * 帮助积赞的人的微信用户ID
     */
	@TableField("helpjizanWxUserID")
	private Long helpjizanWxUserID;
    /**
     * 积赞时间
     */
	@TableField("helpjizanTime")
	private Date helpjizanTime;
    /**
     * 积赞留言
     */
	@TableField("helpjizanLiuyan")
	private String helpjizanLiuyan;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdJizanHelpjizan{" +
			", id=" + id +
			", whdJizanFaqimyjizanId=" + whdJizanFaqimyjizanId +
			", helpjizanWxUserID=" + helpjizanWxUserID +
			", helpjizanTime=" + helpjizanTime +
			", helpjizanLiuyan=" + helpjizanLiuyan +
			", qiyeID=" + qiyeID +
			"}";
	}
}
