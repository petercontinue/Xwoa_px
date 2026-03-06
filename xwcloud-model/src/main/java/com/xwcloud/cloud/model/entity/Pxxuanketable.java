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
 * @since 2021-04-15
 */
@Data
@Accessors(chain = true)
@TableName("pxxuanketable")
public class Pxxuanketable extends Model<Pxxuanketable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 排课ID
     */
	@TableField("paikeID")
	private Long paikeID;
    /**
     * 记录日期
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("recordDate")
	private Date recordDate;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 0.新学员，1.老学员（用于判断班级原学员）
     */
	@TableField("type")
	private Integer type;
    /**
     * 补习课程ID
     */
	@TableField("buxiID")
	private Long buxiID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxxuanketable{" +
			", id=" + id +
			", paikeID=" + paikeID +
			", recordDate=" + recordDate +
			", stuID=" + stuID +
			", type=" + type +
			", buxiID=" + buxiID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
