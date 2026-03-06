package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
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
 * @since 2020-11-25
 */
@Data
@Accessors(chain = true)
public class Pxbxkcchangetable extends Model<Pxbxkcchangetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("oldStuID")
	private Long oldStuID;
	@TableField("oldbxkcID")
	private Long oldbxkcID;
	@TableField("oldbxkcName")
	private String oldbxkcName;
	@TableField("oldkcID")
	private Long oldkcID;
	@TableField("oldprice")
	private BigDecimal oldprice;
	@TableField("oldrkeshi")
	private BigDecimal oldrkeshi;
	@TableField("oldzongjia")
	private BigDecimal oldzongjia;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("oldstartDate")
	private Date oldstartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("oldendDate")
	private Date oldendDate;
	@TableField("oldqiandanID")
	private Long oldqiandanID;
	@TableField("newStuID")
	private Long newStuID;
	@TableField("newbxkcID")
	private Long newbxkcID;
	@TableField("newbxkcName")
	private String newbxkcName;
	@TableField("newkcID")
	private Long newkcID;
	@TableField("newprice")
	private BigDecimal newprice;
	@TableField("newrkeshi")
	private BigDecimal newrkeshi;
	@TableField("newzongjia")
	private BigDecimal newzongjia;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("newstartDate")
	private Date newstartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("newendDate")
	private Date newendDate;
	@TableField("newqiandanID")
	private Long newqiandanID;
    /**
     * 1.新签。2.续费。3.改签单。4.删签单。5.退费。6.添加课程。7.删除课程。8.转送。9.赠送。10.课程改单价。11.课程合并。12.课程换课。13.课程延期。
     */
	@TableField("type")
	private Integer type;
	@TableField("addStaffID")
	private Long addStaffID;
	@TableField("addDate")
	private Date addDate;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxbxkcchangetable{" +
			", id=" + id +
			", oldStuID=" + oldStuID +
			", oldbxkcID=" + oldbxkcID +
			", oldbxkcName=" + oldbxkcName +
			", oldkcID=" + oldkcID +
			", oldprice=" + oldprice +
			", oldrkeshi=" + oldrkeshi +
			", oldzongjia=" + oldzongjia +
			", oldstartDate=" + oldstartDate +
			", oldendDate=" + oldendDate +
			", oldqiandanID=" + oldqiandanID +
			", newStuID=" + newStuID +
			", newbxkcID=" + newbxkcID +
			", newbxkcName=" + newbxkcName +
			", newkcID=" + newkcID +
			", newprice=" + newprice +
			", newrkeshi=" + newrkeshi +
			", newzongjia=" + newzongjia +
			", newstartDate=" + newstartDate +
			", newendDate=" + newendDate +
			", newqiandanID=" + newqiandanID +
			", type=" + type +
			", addStaffID=" + addStaffID +
			", addDate=" + addDate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
