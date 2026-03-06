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
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-18
 */
@Data
@Accessors(chain = true)
@TableName("wsc_dongtaiinfo")
public class WscDongtaiinfo extends Model<WscDongtaiinfo> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 微商城用户ID
     */
	@TableField("wscuserID")
	private Long wscuserID;
    /**
     * 动态标题
     */
	@TableField("dongtaiTitle")
	private String dongtaiTitle;
    /**
     * 动态内容
     */
	@TableField("dongtaiContent")
	private String dongtaiContent;
    /**
     * 发布时间
     */
	@TableField("Addtime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addtime;
    /**
     * 是否置顶：1不置顶；2置顶
     */
	@TableField("iszhiding")
	private Integer iszhiding;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 是否显示：0：显示；1：不显示
     */
	@TableField("isShow")
	private Integer isShow;
    /**
     * 点赞次数
     */
	@TableField("yueduTimes")
	private Integer yueduTimes;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscDongtaiinfo{" +
			", id=" + id +
			", wscuserID=" + wscuserID +
			", dongtaiTitle=" + dongtaiTitle +
			", dongtaiContent=" + dongtaiContent +
			", addtime=" + addtime +
			", iszhiding=" + iszhiding +
			", qiyeID=" + qiyeID +
			", isShow=" + isShow +
			", yueduTimes=" + yueduTimes +
			"}";
	}
}
