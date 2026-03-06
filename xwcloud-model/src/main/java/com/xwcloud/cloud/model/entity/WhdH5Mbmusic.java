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
 * @since 2021-04-01
 */
@Data
@Accessors(chain = true)
@TableName("whd_h5_mbmusic")
public class WhdH5Mbmusic extends Model<WhdH5Mbmusic> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("mbMusicName")
	private String mbMusicName;
	@TableField("mbMusicUrl")
	private String mbMusicUrl;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdH5Mbmusic{" +
			", id=" + id +
			", mbMusicName=" + mbMusicName +
			", mbMusicUrl=" + mbMusicUrl +
			"}";
	}
}
