package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
@Data
@Accessors(chain = true)
@TableName("oa_workrizhi")
public class OaWorkrizhi extends Model<OaWorkrizhi> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 工作汇报说明
     */
    @TableField("workHuibaoShuoming")
    private String workHuibaoShuoming;
    /**
     * 工作计划
     */
    @TableField("workJihua")
    private String workJihua;
    /**
     * 阅读状态，0未读，1已读
     */
    @TableField("readState")
    private Boolean readState;
    /**
     * 1日报，2周报，3月报，4年报，默认1
     */
    @TableField("type")
    private Integer type;

    @TableField("huibaoToStaffID")
    private Long huibaoToStaffID;

    @TableField("addStaffID")
    private Long addStaffID;

    @TableField("addTime")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaWorkrizhi{" +
                ", id=" + id +
                ", workHuibaoShuoming=" + workHuibaoShuoming +
                ", workJihua=" + workJihua +
                ", readState=" + readState +
                ", type=" + type +
                ", addStaffID=" + addStaffID +
                ", addTime=" + addTime +
                "}";
    }
}
