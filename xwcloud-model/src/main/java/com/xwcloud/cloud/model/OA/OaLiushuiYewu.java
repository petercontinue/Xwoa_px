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
 * @since 2021-07-13
 */
@Data
@Accessors(chain = true)
@TableName("oa_liushui_yewu")
public class OaLiushuiYewu extends Model<OaLiushuiYewu> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 流水类别：1培训流水，2不续费流水，3代金券流水，4下次付款时间流水，5加校区流水，6停用服用流水
     */
	@TableField("liushuiType")
	private Integer liushuiType;
    /**
     * 流水说明
     */
	@TableField("liushuishuoming")
	private String liushuishuoming;
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
		return "OaLiushuiYewu{" +
			", id=" + id +
			", qiyeID=" + qiyeID +
			", liushuiType=" + liushuiType +
			", liushuishuoming=" + liushuishuoming +
			", addUser=" + addUser +
			", addTime=" + addTime +
			"}";
	}
}
