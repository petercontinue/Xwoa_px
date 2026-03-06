package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
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
 * @since 2020-11-22
 */
@Data
@Accessors(chain = true)
public class Pxkeshizhuansongtable extends Model<Pxkeshizhuansongtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 送出的学员ID
     */
	@TableField("songstuID")
	private Long songstuID;
    /**
     * 送出的课程ID
     */
	@TableField("songkechengID")
	private Long songkechengID;
    /**
     * 送出课程的单价
     */
	@TableField("songkechengPrice")
	private BigDecimal songkechengPrice;
    /**
     * 送出的课时
     */
	@TableField("songKeshiNum")
	private BigDecimal songKeshiNum;
    /**
     * 送出的学费
     */
	@TableField("songXueFei")
	private BigDecimal songXueFei;
    /**
     * 接收的学员ID
     */
	@TableField("shoustuID")
	private Long shoustuID;
    /**
     * 接收的课程ID
     */
	@TableField("shoukechengID")
	private Long shoukechengID;
    /**
     * 接收的课程单价
     */
	@TableField("shoukechengPrice")
	private BigDecimal shoukechengPrice;
    /**
     * 接收的课程学费
     */
	@TableField("shouXueFei")
	private BigDecimal shouXueFei;
    /**
     * 接收的课程课时
     */
	@TableField("shouKeshi")
	private BigDecimal shouKeshi;
    /**
     * 转送时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("zhuansongDate")
	private Date zhuansongDate;
    /**
     * 转送说明
     */
	@TableField("shuoMing")
	private String shuoMing;
    /**
     * 转送信息
     */
	@TableField("zhuansongXinXi")
	private String zhuansongXinXi;
    /**
     * 添加时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addtime")
	private Date addtime;
    /**
     * 添加人
     */
	@TableField("adduser")
	private Long adduser;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxkeshizhuansongtable{" +
			", id=" + id +
			", songstuID=" + songstuID +
			", songkechengID=" + songkechengID +
			", songkechengPrice=" + songkechengPrice +
			", songKeshiNum=" + songKeshiNum +
			", songXueFei=" + songXueFei +
			", shoustuID=" + shoustuID +
			", shoukechengID=" + shoukechengID +
			", shoukechengPrice=" + shoukechengPrice +
			", shouXueFei=" + shouXueFei +
			", shouKeshi=" + shouKeshi +
			", zhuansongDate=" + zhuansongDate +
			", shuoMing=" + shuoMing +
			", zhuansongXinXi=" + zhuansongXinXi +
			", addtime=" + addtime +
			", adduser=" + adduser +
			", qiyeID=" + qiyeID +
			"}";
	}
}
