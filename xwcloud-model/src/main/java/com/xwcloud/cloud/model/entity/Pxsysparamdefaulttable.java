package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
@Data
@Accessors(chain = true)
@TableName("pxsysparamdefaulttable")
public class Pxsysparamdefaulttable extends Model<Pxsysparamdefaulttable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 变量名
     */
	@TableField("bianLiangName")
	private String bianLiangName;
    /**
     * 变量的【默认值】
     */
	@TableField("defaultValue")
	private String defaultValue;
    /**
     * 变量说明
     */
	@TableField("ParameterContent")
	private String parameterContent;
    /**
     * 404表示不显示
     */
	@TableField("type")
	private String type;
	@TableField("paixu")
	private String paixu;

}
