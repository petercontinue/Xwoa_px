package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-07-02
 */
@Data
@Accessors(chain = true)
@TableName("oa_hehuoren_level")
public class OaHehuorenLevel extends Model<OaHehuorenLevel> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 合伙级别
     */
	@TableField("hehuoLevel")
	private String hehuoLevel;
    /**
     * 返佣比例
     */
	@TableField("fangyongbili")
	private BigDecimal fangyongbili;
    /**
     * 备注说明
     */
	@TableField("shuoming")
	private String shuoming;
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
		return "OaHehuorenLevel{" +
			", id=" + id +
			", hehuoLevel=" + hehuoLevel +
			", fangyongbili=" + fangyongbili +
			", shuoming=" + shuoming +
			", addUser=" + addUser +
			", addTime=" + addTime +
			"}";
	}
}
