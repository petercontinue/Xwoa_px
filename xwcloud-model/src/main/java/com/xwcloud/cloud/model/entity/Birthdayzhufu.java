package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * @since 2021-05-18
 */
@Data
@Accessors(chain = true)
@TableName("birthdayzhufu")
public class Birthdayzhufu extends Model<Birthdayzhufu> {

    private static final long serialVersionUID = 1L;

	@JsonSerialize(using= ToStringSerializer.class)
    @TableId("id")
	private Long id;
    /**
     * 祝福用户ID
     */
	@TableField("zhufuUserID")
	private Long zhufuUserID;
    /**
     * 祝福语内容
     */
	@TableField("zhufuContent")
	private String zhufuContent;
    /**
     * 留言用户ID
     */
	@TableField("liuyanUserid")
	private Long liuyanUserid;
    /**
     * 留言时间
     */
	@TableField("liuyanTimes")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date liuyanTimes;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Birthdayzhufu{" +
			", id=" + id +
			", zhufuUserID=" + zhufuUserID +
			", zhufuContent=" + zhufuContent +
			", liuyanUserid=" + liuyanUserid +
			", liuyanTimes=" + liuyanTimes +
			"}";
	}
}
