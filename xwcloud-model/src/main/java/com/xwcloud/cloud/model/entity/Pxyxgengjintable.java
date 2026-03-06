package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
@TableName("pxyxgengjintable")
public class Pxyxgengjintable extends Model<Pxyxgengjintable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("stuID")
	private Long stuID;
	@TableField("gengjinText")
	private String gengjinText;
	@TableField("gengjinTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gengjinTime;
	@TableField("adduser")
	private Long adduser;
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
    /**
     * 阅读状态
     */
	@TableField("isRead")
	private Boolean isRead;
	@TableField("qiyeID")
	private Long qiyeID;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxyxgengjintable{" +
			", id=" + id +
			", stuID=" + stuID +
			", gengjinText=" + gengjinText +
			", gengjinTime=" + gengjinTime +
			", adduser=" + adduser +
			", addTime=" + addTime +
			", isRead=" + isRead +
			", qiyeID=" + qiyeID +
			"}";
	}
}
