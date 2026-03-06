package com.xwcloud.cloud.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-01-27
 */
@Data
@Accessors(chain = true)
@TableName("pxtingke")
public class Pxtingke extends Model<Pxtingke> {

    private static final long serialVersionUID = 1L;

    /**
     * 停课记录表ID
     */
    @TableId("id")
    private Long id;
    /**
     * 类型 1停课  2休眠
     */
    @TableField("type")
    private Integer type;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 停课（休眠）添加人
     */
    @TableField("tingkeUser")
    private Long tingkeUser;
    /**
     * 停课（休眠）时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("tingkeTime")
    private Date tingkeTime;
    /**
     * 停课（休眠）说明
     */
    @TableField("tingkeshuoming")
    private String tingkeshuoming;
    /**
     * 复课时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("fukeTime")
    private Date fukeTime;
    /**
     * 复课说明
     */
    @TableField("fukeshuoming")
    private String fukeshuoming;
    /**
     * 复课添加人
     */
    @TableField("fukeUser")
    private Long fukeUser;
    /**
     * qiyeID
     */
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxtingke{" +
                ", id=" + id +
                ", type=" + type +
                ", stuID=" + stuID +
                ", tingkeUser=" + tingkeUser +
                ", tingkeTime=" + tingkeTime +
                ", tingkeshuoming=" + tingkeshuoming +
                ", fukeTime=" + fukeTime +
                ", fukeshuoming=" + fukeshuoming +
                ", fukeUser=" + fukeUser +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
