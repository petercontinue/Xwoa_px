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
@TableName("whd_jizan_faqimyjizan")
public class WhdJizanFaqimyjizan extends Model<WhdJizanFaqimyjizan> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 积赞活动ID，whd_jizan_huodong表的ID
     */
	@TableField("whd_jizan_huodong_id")
	private Long whdJizanHuodongId;
    /**
     * 发起人的微信用户ID
     */
	@TableField("wxUserIDFaqiren")
	private Long wxUserIDFaqiren;
    /**
     * 集赞宣言
     */
	@TableField("jizanxuanyan")
	private String jizanxuanyan;
    /**
     * 发起时间
     */
	@TableField("addTime")
	private Date addTime;

	@TableField("dianzantimes")
	private int dianzantimes;

	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdJizanFaqimyjizan{" +
			", id=" + id +
			", whdJizanHuodongId=" + whdJizanHuodongId +
			", wxUserIDFaqiren=" + wxUserIDFaqiren +
			", jizanxuanyan=" + jizanxuanyan +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
