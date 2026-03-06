package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-11-04
 */
@Data
@Accessors(chain = true)
public class Pxroomtable extends Model<Pxroomtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 校区ID
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 宿舍号，如：A-1-101
     */
	@TableField("number")
	private String number;
    /**
     * 最大人数
     */
	@TableField("renshu")
	private Integer renshu;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("addTime")
	private Date addTime;
	@TableField("addStaffID")
	private Long addStaffID;
	@TableField("qiyeID")
	private Long qiyeID;

}
