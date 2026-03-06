package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>a
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Data
@Accessors(chain = true)
@TableName("whd_toupiao_tprecord")
public class WhdToupiaoTprecord extends Model<WhdToupiaoTprecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 参赛学员ID，对应whd_toupiao_cansaistu表的id
     */
	@TableField("canSaiStuID")
	private Long cansaistuid;
    /**
     * 微商城用户ID
     */
	@TableField("wscUserID")
	private Long wscuserid;
    /**
     * 投票时间
     */
	@TableField("toupiaoTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date toupiaotime;
    /**
     * 投票活动ID
     */
	@TableField("toupiaoHuodongID")
	private Long toupiaohuodongid;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdToupiaoTprecord{" +
			", id=" + id +
			", cansaistuid=" + cansaistuid +
			", wscuserid=" + wscuserid +
			", toupiaotime=" + toupiaotime +
			", toupiaohuodongid=" + toupiaohuodongid +
			", qiyeid=" + qiyeID +
			"}";
	}
}
