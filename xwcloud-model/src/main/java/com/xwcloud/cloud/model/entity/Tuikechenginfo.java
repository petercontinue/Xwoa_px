package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-04-08
 */
@Data
@Accessors(chain = true)
@TableName("tuikechenginfo")
public class Tuikechenginfo extends Model<Tuikechenginfo> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 退费学员
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 补习课程ID 结课时学员有几个补习课程加几条记录
     */
	@TableField("tuibxkechengID")
	private Long tuibxkechengID;
    /**
     * 退费钱剩余课时数
     */
	@TableField("remainkeshi")
	private BigDecimal remainkeshi;
    /**
     * 每个课程退的课时数
     */
	@TableField("tuikeshi")
	private BigDecimal tuikeshi;
    /**
     * 每个课程的退费单价
     */
	@TableField("tuikechengPrice")
	private BigDecimal tuikechengPrice;
    /**
     * 添加人
     */
	@TableField("adduser")
	private Long adduser;
    /**
     * 添加时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addTime")
	private Date addTime;
    /**
     * 退费审批表ID
     */
	@TableField("tuifeispID")
	private Long tuifeispID;
    /**
     * 退费的课程金额
     */
	@TableField("tuimoney")
	private BigDecimal tuimoney;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Tuikechenginfo{" +
			", id=" + id +
			", stuID=" + stuID +
			", tuibxkechengID=" + tuibxkechengID +
			", remainkeshi=" + remainkeshi +
			", tuikeshi=" + tuikeshi +
			", tuikechengPrice=" + tuikechengPrice +
			", adduser=" + adduser +
			", addTime=" + addTime +
			", tuifeispID=" + tuifeispID +
			", tuimoney=" + tuimoney +
			", qiyeID=" + qiyeID +
			"}";
	}
}
