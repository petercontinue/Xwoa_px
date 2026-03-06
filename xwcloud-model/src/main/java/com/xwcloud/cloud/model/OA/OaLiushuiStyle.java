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
 * @since 2021-07-15
 */
@Data
@Accessors(chain = true)
@TableName("oa_liushui_style")
public class OaLiushuiStyle extends Model<OaLiushuiStyle> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("liushuiStyle")
	private String liushuiStyle;
    /**
     * 1收入,2支出
     */
	@TableField("isShouruOrZhichu")
	private Integer isShouruOrZhichu;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OaLiushuiStyle{" +
			", id=" + id +
			", liushuiStyle=" + liushuiStyle +
			", isShouruOrZhichu=" + isShouruOrZhichu +
			"}";
	}
}
