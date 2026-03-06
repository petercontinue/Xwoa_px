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
@TableName("oa_huifang")
public class OaHuifang extends Model<OaHuifang> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("addTime")
	private Date addTime;
	@TableField("addStaffID")
	private Long addStaffID;
    /**
     * 回访内容
     */
	@TableField("huifangContent")
	private String huifangContent;
    /**
     * 回访时间
     */
	@TableField("huifangDatetime")
	private Date huifangDatetime;
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 备注说明
     */
	@TableField("shuoming")
	private String shuoming;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaHuifang{" +
			", id=" + id +
			", addTime=" + addTime +
			", addStaffID=" + addStaffID +
			", huifangContent=" + huifangContent +
			", huifangDatetime=" + huifangDatetime +
			", qiyeID=" + qiyeID +
			", shuoming=" + shuoming +
			"}";
	}
}
