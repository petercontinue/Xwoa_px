package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Data
@Accessors(chain = true)
@TableName("oa_taocantype")
public class OaTaocantype extends Model<OaTaocantype> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 套餐名称
     */
	@TableField("taocanName")
	private String taocanName;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
