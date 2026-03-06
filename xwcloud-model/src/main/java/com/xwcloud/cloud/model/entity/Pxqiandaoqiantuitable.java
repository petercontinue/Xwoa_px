package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-09
 */
@Data
@Accessors(chain = true)
@TableName("pxqiandaoqiantuitable")
public class Pxqiandaoqiantuitable extends Model<Pxqiandaoqiantuitable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuid;
    /**
     * 签到还是签退，1签到，2签退
     */
    @TableField("qiandaoOrqiantui")
    private Integer qiandaoorqiantui;
    /**
     * 签到或签退时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("qianDatetime")
    private Date qiandatetime;
    /**
     * 签到签退方式：1.刷卡， 2.微信，3.人工
     */
    @TableField("qianStyle")
    private Integer qianstyle;
    /**
     * 微信消息推送状态,true推送成功，false推送失败
     */
    @TableField("tsState")
    private Boolean tsstate;
    /**
     * 排课ID，非必填
     */
    @TableField("paikeID")
    private Long paikeid;
    /**
     * 添加人
     */
    @TableField("addstaffID")
    private Long addstaffid;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxqiandaoqiantuitable{" +
                ", id=" + id +
                ", stuid=" + stuid +
                ", qiandaoorqiantui=" + qiandaoorqiantui +
                ", qiandatetime=" + qiandatetime +
                ", qianstyle=" + qianstyle +
                ", tsstate=" + tsstate +
                ", paikeid=" + paikeid +
                ", addstaffid=" + addstaffid +
                ", qiyeid=" + qiyeID +
                "}";
    }
}
