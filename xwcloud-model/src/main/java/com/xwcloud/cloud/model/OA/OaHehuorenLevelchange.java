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
 * @since 2021-07-02
 */
@Data
@Accessors(chain = true)
@TableName("oa_hehuoren_levelchange")
public class OaHehuorenLevelchange extends Model<OaHehuorenLevelchange> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 原合伙人级别ID
     */
	@TableField("oldLevelID")
	private Long oldLevelID;
    /**
     * 新合伙人级别ID
     */
	@TableField("newLevelID")
	private Long newLevelID;
    /**
     * 级别变动原因
     */
	@TableField("levelChangeReason")
	private String levelChangeReason;
    /**
     * 级别变动日期
     */
	@TableField("changeTime")
	private Date changeTime;
    /**
     * 备注
     */
	@TableField("beizhu")
	private String beizhu;
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
		return "OaHehuorenLevelchange{" +
			", id=" + id +
			", oldLevelID=" + oldLevelID +
			", newLevelID=" + newLevelID +
			", levelChangeReason=" + levelChangeReason +
			", changeTime=" + changeTime +
			", beizhu=" + beizhu +
			", addUser=" + addUser +
			", addTime=" + addTime +
			"}";
	}
}
