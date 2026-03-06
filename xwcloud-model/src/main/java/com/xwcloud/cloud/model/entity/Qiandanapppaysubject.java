package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Data
@Accessors(chain = true)
@TableName("qiandanapppaysubject")
public class Qiandanapppaysubject extends Model<Qiandanapppaysubject> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 签单ApppayID
     */
    @TableField("qiandanAppayID")
    private Long qiandanAppayID;
    /**
     * 学生ID
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 签单日期
     */
    @TableField("qiandandate")
    private Date qiandandate;
    /**
     * 课程ID
     */
    @TableField("kechengID")
    private Long kechengID;
    /**
     * 课程销售价（实际成交价，即分摊了优惠后的单价）
     */
    @TableField("kechengprice")
    private BigDecimal kechengprice;
    /**
     * 课程原价
     */
    @TableField("originalprice")
    private BigDecimal originalprice;
    /**
     * 购买课时数
     */
    @TableField("buykeshiNum")
    private BigDecimal buykeshiNum;
    /**
     * 赠送课时数
     */
    @TableField("zengsongkeshi")
    private BigDecimal zengsongkeshi;
    /**
     * 总价
     */
    @TableField("zongjia")
    private BigDecimal zongjia;
    /**
     * 开始日期
     */
    @TableField("startDate")
    private Date startDate;
    /**
     * 结束日期
     */
    @TableField("endDate")
    private Date endDate;
    /**
     * 1买的 2 接受的赠送 3 送出的 4 退费 5 换课换出 6 换课得到
     */
    @TableField("kechengStyle")
    private Integer kechengStyle;
    /**
     * 折扣,10表示没打折
     */
    @TableField("discount")
    private BigDecimal discount;
    /**
     * 班级名称
     */
    @TableField("classID")
    private Long classID;

    @TableField("pkid")
    private Long pkid;
    /**
     * 是否插入排课
     */
    @TableField("charukebiao")
    private Boolean charukebiao;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Qiandanapppaysubject{" +
                ", id=" + id +
                ", qiandanAppayID=" + qiandanAppayID +
                ", stuID=" + stuID +
                ", qiandandate=" + qiandandate +
                ", kechengID=" + kechengID +
                ", kechengprice=" + kechengprice +
                ", originalprice=" + originalprice +
                ", buykeshiNum=" + buykeshiNum +
                ", zengsongkeshi=" + zengsongkeshi +
                ", zongjia=" + zongjia +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", kechengStyle=" + kechengStyle +
                ", discount=" + discount +
                ", classID=" + classID +
                ", pkid=" + pkid +
                ", charukebiao=" + charukebiao +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
