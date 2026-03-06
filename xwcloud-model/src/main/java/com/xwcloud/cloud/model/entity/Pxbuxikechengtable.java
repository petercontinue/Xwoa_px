package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("pxbuxikechengtable")
public class Pxbuxikechengtable extends Model<Pxbuxikechengtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 课程ID
     */
    @TableField("kechengID")
    private Long kechengID;
    /**
     * 课程原价
     */
    @TableField("originalprice")
    private BigDecimal originalprice;
    /**
     * 课程销售价（实际成交价，即分摊了优惠后的单价）
     */
    @TableField("kechengprice")
    private BigDecimal kechengprice;
    @TableField("remainkeshi")
    private BigDecimal remainkeshi;
    /**
     * 课时数
     */
    @TableField("keshiNum")
    private BigDecimal keshiNum;
    /**
     * 总价
     */
    @TableField("zongjia")
    private BigDecimal zongjia;
    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("startDate")
    private Date startDate;
    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("endDate")
    private Date endDate;
    /**
     * 课时购买时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("buykeshiDateTime")
    private Date buykeshiDateTime;
    /**
     * 是否启用，1启用，0不启用，默认值1
     */
    @TableField("isShow")
    private Integer isShow;
    /**
     * 课程计费方式，1按课时，2按课时包，3按起止日期
     */
    @TableField("jifeiStyleID")
    private Integer jifeiStyleID;
    /**
     * 类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
     */
    @TableField("type")
    private Integer type;
    /**
     * 签单课程表ID
     */
    @TableField("qianDanSubjectID")
    private Long qianDanSubjectID;
    /**
     * 签单信息表ID
     */
    @TableField("qianDanInfoID")
    private Long qianDanInfoID;

    /**
     * 共享buxiID
     * 默认值0表示不共享,和哪个补习课程ID的课时共享，也用于课程切换使用。优先扣减自己的课时，扣完了自己的再扣减共享的,共享的也扣完了的话，就再切换到和自己的shareBuxiID相等的课程
     */
    @TableField("shareBuxiID")
    private String shareBuxiID;

    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxbuxikechengtable{" +
                ", id=" + id +
                ", stuID=" + stuID +
                ", kechengID=" + kechengID +
                ", originalprice=" + originalprice +
                ", kechengprice=" + kechengprice +
                ", remainkeshi=" + remainkeshi +
                ", keshiNum=" + keshiNum +
                ", zongjia=" + zongjia +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", buykeshiDateTime=" + buykeshiDateTime +
                ", isShow=" + isShow +
                ", jifeiStyleID=" + jifeiStyleID +
                ", type=" + type +
                ", qianDanSubjectID=" + qianDanSubjectID +
                ", qianDanInfoID=" + qianDanInfoID +
                ", shareBuxiID=" + shareBuxiID +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
