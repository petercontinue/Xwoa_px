package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2021-05-21
 */
@Data
@Accessors(chain = true)
@TableName("wsc_kanjia_bangkanrecord")
public class WscKanjiaBangkanrecord extends Model<WscKanjiaBangkanrecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 砍价发起ID
     */
	@TableField("kanjiaFaqiID")
	private Long kanjiaFaqiID;
    /**
     * 帮砍人微商城用户ID
     */
	@TableField("bangkanrenWxUserID")
	private Long bangkanrenWxUserID;
    /**
     * 一刀砍了多少钱
     */
	@TableField("kanMoney")
	private BigDecimal kanMoney;
    /**
     * 帮砍时间
     */
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;

	private String nickName;
	private String headImage;
	private String bankanName;
	private String faqiName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscKanjiaBangkanrecord{" +
			", id=" + id +
			", kanjiaFaqiID=" + kanjiaFaqiID +
			", bangkanrenWxUserID=" + bangkanrenWxUserID +
			", kanMoney=" + kanMoney +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
