package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-05-26
 */
@Data
@Accessors(chain = true)
@TableName("wsc_userguanzhu")
public class WscUserguanzhu extends Model<WscUserguanzhu> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 微商城用户ID
     */
	@TableField("wscuserID")
	private Long wscuserID;
    /**
     * 被关注用户ID
     */
	@TableField("beiguanzhuUserID")
	private Long beiguanzhuUserID;
    /**
     * 关注时间
     */
	@TableField("guanzhuDatetine")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date guanzhuDatetine;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscUserguanzhu{" +
			", id=" + id +
			", wscuserID=" + wscuserID +
			", beiguanzhuUserID=" + beiguanzhuUserID +
			", guanzhuDatetine=" + guanzhuDatetine +
			"}";
	}
}
