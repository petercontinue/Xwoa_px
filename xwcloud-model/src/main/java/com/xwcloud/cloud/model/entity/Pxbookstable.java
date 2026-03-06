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
 * @since 2020-11-13
 */
@Data
@Accessors(chain = true)
public class Pxbookstable extends Model<Pxbookstable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("campusID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long campusID;
	@TableField("booksName")
	private String booksName;
    /**
     * 总本数
     */
	@TableField("allnum")
	private Integer allnum;
    /**
     * 剩余数,当前还有几本
     */
	@TableField("cunnum")
	private Integer cunnum;
    /**
     * 是否带答案
     */
	@TableField("iSdaance")
	private Boolean iSdaance;
    /**
     * 是否带光盘
     */
	@TableField("iSdisc")
	private Boolean iSdisc;
    /**
     * 作者
     */
	@TableField("author")
	private String author;
    /**
     * 出版社
     */
	@TableField("press")
	private String press;
    /**
     * 出版日期
     */
	@TableField("chubanDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date chubanDate;
    /**
     * 版次
     */
	@TableField("banci")
	private String banci;
    /**
     * ISBN
     */
	@TableField("isbn")
	private String isbn;
    /**
     * 图书存放位置编码
     */
	@TableField("bookLocationCode")
	private String bookLocationCode;
    /**
     * 说明
     */
	@TableField("shuoming")
	private String shuoming;
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxbookstable{" +
			", id=" + id +
			", campusID=" + campusID +
			", booksName=" + booksName +
			", allnum=" + allnum +
			", cunnum=" + cunnum +
			", iSdaance=" + iSdaance +
			", iSdisc=" + iSdisc +
			", author=" + author +
			", press=" + press +
			", chubanDate=" + chubanDate +
			", banci=" + banci +
			", isbn=" + isbn +
			", bookLocationCode=" + bookLocationCode +
			", shuoming=" + shuoming +
			", qiyeID=" + qiyeID +
			"}";
	}
}
