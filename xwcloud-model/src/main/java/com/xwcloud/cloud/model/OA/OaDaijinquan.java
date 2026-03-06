package com.xwcloud.cloud.model.OA;

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
 * @since 2021-07-02
 */
@Data
@Accessors(chain = true)
@TableName("oa_daijinquan")
public class OaDaijinquan extends Model<OaDaijinquan> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 得到代金券金额，默认值0
     */
    @TableField("getDjq")
    private BigDecimal getDjq;
    /**
     * 使用代金券金额，默认值0
     */
    @TableField("useDjq")
    private BigDecimal useDjq;
    /**
     * 说明
     */
    @TableField("shuoming")
    private String shuoming;
    /**
     * 添加人
     */
    @TableField("addUser")
    private Long addUser;
    /**
     * 添加时间
     */
    @TableField("addTime")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaDaijinquan{" +
                ", id=" + id +
                ", qiyeID=" + qiyeID +
                ", getDjq=" + getDjq +
                ", useDjq=" + useDjq +
                ", shuoming=" + shuoming +
                ", addUser=" + addUser +
                ", addTime=" + addTime +
                "}";
    }
}
