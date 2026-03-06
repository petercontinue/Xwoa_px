package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-01
 */
@Data
@Accessors(chain = true)
public class Pxyxqiandantable extends Model<Pxyxqiandantable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("stuID")
	private Long stuID;

	/**
	 * 签单金额
	 */
	@TableField("qianDanMoney")
	private String qianDanMoney;

	@TableField("isBaomingOrChongzhi")
	private Integer isBaomingOrChongzhi;

	@TableField("yxShichangRenID")
	private Long yxShichangRenID;
	@TableField("yxDengjiRenID")
	private Long yxDengjiRenID;
	@TableField("yxFuzheRenID")
	private Long yxFuzheRenID;
	@TableField("yxQiandanRenID")
	private Long yxQiandanRenID;
	@TableField("yxDengjiDateTime")
	private Date yxDengjiDateTime;
	@TableField("yxFenpeiDateTime")
	private Date yxFenpeiDateTime;
	@TableField("yxQiandanDateTime")
	private Date yxQiandanDateTime;

	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}


}
