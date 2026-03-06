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
 * @since 2021-08-06
 */
@Data
@Accessors(chain = true)
@TableName("minganwords")
public class Minganwords extends Model<Minganwords> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 敏感词
     */
	@TableField("word")
	private String word;
    /**
     * 是否有效，1有效，0无效，默认值1
     */
	@TableField("isYouxiao")
	private Integer isYouxiao;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Minganwords{" +
			", id=" + id +
			", word=" + word +
			", isYouxiao=" + isYouxiao +
			"}";
	}
}
