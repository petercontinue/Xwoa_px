package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("whd_choujiang_jiangping")
public class WhdChoujiangJiangping extends Model<WhdChoujiangJiangping> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 奖品等级
     */
	@TableField("jiangpingLevel")
	private String jiangpinglevel;
    /**
     * 奖品名称
     */
	@TableField("jiangpingName")
	private String jiangpingname;
    /**
     * 奖品图片
     */
	@TableField("jiangpingImg")
	private String jiangpingimg;
    /**
     * 抽奖活动ID
     */
	@TableField("choujiangHuodongID")
	private Long choujianghuodongid;
    /**
     * 中奖概率
     */
	@TableField("zhongjiangGailv")
	private String zhongjianggailv;
    /**
     * 奖品数量
     */
	@TableField("jiangpingTotalNum")
	private Integer jiangpingtotalnum;
    /**
     * 0：不是奖品，1：是奖品
     */
	@TableField("type")
	private Integer type;
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 添加人
     */
	@TableField("addUser")
	private Long adduser;
    /**
     * 添加时间
     */
	@TableField("addTime")
	private Date addtime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdChoujiangJiangping{" +
			", id=" + id +
			", jiangpinglevel=" + jiangpinglevel +
			", jiangpingname=" + jiangpingname +
			", jiangpingimg=" + jiangpingimg +
			", choujianghuodongid=" + choujianghuodongid +
			", zhongjianggailv=" + zhongjianggailv +
			", jiangpingtotalnum=" + jiangpingtotalnum +
			", type=" + type +
			", qiyeid=" + qiyeID +
			", adduser=" + adduser +
			", addtime=" + addtime +
			"}";
	}
}
