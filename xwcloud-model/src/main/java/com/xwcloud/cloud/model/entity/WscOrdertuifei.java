package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-23
 */
@Data
@Accessors(chain = true)
@TableName("wsc_ordertuifei")
public class WscOrdertuifei extends Model<WscOrdertuifei> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 订单号
     */
	@TableField("orderNumber")
	private Long orderNumber;
    /**
     * 退款申请时间
     */
	@TableField("tuikuanTime")
	private LocalDateTime tuikuanTime;
    /**
     * 退款申请说明
     */
	@TableField("tuikuanShuoming")
	private String tuikuanShuoming;
    /**
     * 退款申请人
     */
	@TableField("tuikuanRen")
	private Long tuikuanRen;
    /**
     * 退款申请消息
     */
	@TableField("tuikuanMsg")
	private String tuikuanMsg;
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 退货处理时间
     */
	@TableField("chuliTuihuoTime")
	private LocalDateTime chuliTuihuoTime;
    /**
     * 退货处理说明
     */
	@TableField("chuliTuihuoShuoming")
	private String chuliTuihuoShuoming;
    /**
     * 退货处理人
     */
	@TableField("chuliTuihuoRen")
	private Long chuliTuihuoRen;
    /**
     * 退货处理消息
     */
	@TableField("chuliTuihuoMsg")
	private String chuliTuihuoMsg;
    /**
     * 0.待处理，1.已退货，2拒绝退货
     */
	@TableField("tuihuoState")
	private Integer tuihuoState;
    /**
     * 0.待处理，1.退款中，2拒绝退款，3退款成功，4退款失败，
     */
	@TableField("tuikuanState")
	private Integer tuikuanState;
    /**
     * 退款处理时间
     */
	@TableField("chuliTuikuanTime")
	private LocalDateTime chuliTuikuanTime;
    /**
     * 退款处理说明
     */
	@TableField("chuliTuikuanShuoming")
	private String chuliTuikuanShuoming;
    /**
     * 退款处理人
     */
	@TableField("chuliTuikuanRen")
	private Long chuliTuikuanRen;
    /**
     * 退款处理消息
     */
	@TableField("chuliTuikuanMsg")
	private String chuliTuikuanMsg;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscOrdertuifei{" +
			", id=" + id +
			", orderNumber=" + orderNumber +
			", tuikuanTime=" + tuikuanTime +
			", tuikuanShuoming=" + tuikuanShuoming +
			", tuikuanRen=" + tuikuanRen +
			", tuikuanMsg=" + tuikuanMsg +
			", qiyeID=" + qiyeID +
			", chuliTuihuoTime=" + chuliTuihuoTime +
			", chuliTuihuoShuoming=" + chuliTuihuoShuoming +
			", chuliTuihuoRen=" + chuliTuihuoRen +
			", chuliTuihuoMsg=" + chuliTuihuoMsg +
			", tuihuoState=" + tuihuoState +
			", tuikuanState=" + tuikuanState +
			", chuliTuikuanTime=" + chuliTuikuanTime +
			", chuliTuikuanShuoming=" + chuliTuikuanShuoming +
			", chuliTuikuanRen=" + chuliTuikuanRen +
			", chuliTuikuanMsg=" + chuliTuikuanMsg +
			"}";
	}
}
