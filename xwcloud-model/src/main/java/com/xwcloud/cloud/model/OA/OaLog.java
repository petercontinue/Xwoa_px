package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("oa_log")
public class OaLog extends Model<OaLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 日志内容，给系统使用者看的
     */
    @TableField("systemContent")
    private String systemContent;
    /**
     * 功能板块的名称
     */
    @TableField("funcName")
    private String funcName;
    /**
     * 员工操作产生的日志，填写上员工ID
     */
    @TableField("staffID")
    private Long staffID;
    /**
     * 员工姓名
     */
    @TableField("staffName")
    private String staffName;
    /**
     * 员工日志1，系统自动产生的日志2
     */
    @TableField("logType")
    private Integer logType;
    /**
     * 日志时间
     */
    @TableField("addTime")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaLog{" +
                ", id=" + id +
                ", systemContent=" + systemContent +
                ", funcName=" + funcName +
                ", staffID=" + staffID +
                ", staffName=" + staffName +
                ", logType=" + logType +
                ", addTime=" + addTime +
                "}";
    }
}
