package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-19
 */
@Data
@Accessors(chain = true)
@TableName("oa_parameter")
public class OaParameter extends Model<OaParameter> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 变量名称
     */
	@TableField("bianLiangName")
	private String bianLiangName;
    /**
     * 变量值
     */
	@TableField("modifyValue")
	private String modifyValue;
    /**
     * 变量说明
     */
	@TableField("shuoming")
	private String shuoming;
    /**
     * 类别，默认NULL，404表示不显示
     */
	@TableField("type")
	private String type;
    /**
     * 排序
     */
	@TableField("paixu")
	private Integer paixu;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaParameter{" +
			", id=" + id +
			", bianLiangName=" + bianLiangName +
			", modifyValue=" + modifyValue +
			", shuoming=" + shuoming +
			", type=" + type +
			", paixu=" + paixu +
			"}";
	}
}
