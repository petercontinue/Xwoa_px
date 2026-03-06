package com.xwcloud.cloud.model.log;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
public class Logxiugaijilutable extends Model<Logxiugaijilutable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private String id;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private String stuID;
    /**
     * 这员姓名
     */
	@TableField("stuName")
	private String stuName;
    /**
     * 修改前的数据
     */
	@TableField("xiugaiqianxinxi")
	private String xiugaiqianxinxi;
    /**
     * 修改后的数据
     */
	@TableField("xiugaihouxinxi")
	private String xiugaihouxinxi;
    /**
     * 操作老师
     */
	@TableField("xiugaistaffID")
	private String xiugaistaffID;
    /**
     * 操作时间
     */
	@TableField("xiugaidateTime")
	private Date xiugaidateTime;
    /**
     * 1:添加，2修改，3删除，4导入，5退费
     */
	@TableField("type")
	private Integer type;
	@TableField("qiyeID")
	private String qiyeID;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStuID() {
		return stuID;
	}

	public void setStuID(String stuID) {
		this.stuID = stuID;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getXiugaiqianxinxi() {
		return xiugaiqianxinxi;
	}

	public void setXiugaiqianxinxi(String xiugaiqianxinxi) {
		this.xiugaiqianxinxi = xiugaiqianxinxi;
	}

	public String getXiugaihouxinxi() {
		return xiugaihouxinxi;
	}

	public void setXiugaihouxinxi(String xiugaihouxinxi) {
		this.xiugaihouxinxi = xiugaihouxinxi;
	}

	public String getXiugaistaffID() {
		return xiugaistaffID;
	}

	public void setXiugaistaffID(String xiugaistaffID) {
		this.xiugaistaffID = xiugaistaffID;
	}

	public Date getXiugaidateTime() {
		return xiugaidateTime;
	}

	public void setXiugaidateTime(Date xiugaidateTime) {
		this.xiugaidateTime = xiugaidateTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getQiyeID() {
		return qiyeID;
	}

	public void setQiyeID(String qiyeID) {
		this.qiyeID = qiyeID;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Logxiugaijilutable{" +
			", id=" + id +
			", stuID=" + stuID +
			", stuName=" + stuName +
			", xiugaiqianxinxi=" + xiugaiqianxinxi +
			", xiugaihouxinxi=" + xiugaihouxinxi +
			", xiugaistaffID=" + xiugaistaffID +
			", xiugaidateTime=" + xiugaidateTime +
			", type=" + type +
			", qiyeID=" + qiyeID +
			"}";
	}
}
