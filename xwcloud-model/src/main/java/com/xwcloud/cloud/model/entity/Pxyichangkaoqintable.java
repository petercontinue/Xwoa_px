package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-18
 */
@Data
@Accessors(chain = true)
public class Pxyichangkaoqintable extends Model<Pxyichangkaoqintable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 员工ID
     */
    @TableField("staffID")
    private Long staffID;
    /**
     * 1.请假，2.迟到，3.早退，4.旷工
     */
    @TableField("type")
    private Integer type;
    /**
     * 日期
     */
    @TableField("riqi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date riqi;
    /**
     * 说明
     */
    @TableField("shuoming")
    private String shuoming;
    @TableField("addDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;
    @TableField("addstaffID")
    private Long addstaffID;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxyichangkaoqintable{" +
                ", id=" + id +
                ", staffID=" + staffID +
                ", type=" + type +
                ", riqi=" + riqi +
                ", shuoming=" + shuoming +
                ", addDate=" + addDate +
                ", addstaffID=" + addstaffID +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
