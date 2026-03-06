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
 * @since 2021-07-12
 */
@Data
@Accessors(chain = true)
@TableName("oa_bug_advice")
public class OaBugAdvice extends Model<OaBugAdvice> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 1bug,2功能建议
     */
    @TableField("isBugOrAdvice")
    private Integer isBugOrAdvice;
    /**
     * bug或功能建议的具体内容
     */
    @TableField("content")
    private String content;
    /**
     * 是哪家客户反应的。选填
     */
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 客户什么时间反馈的，选填
     */
    @TableField("kehufankuiDateTime")
    private Date kehufankuiDateTime;
    @TableField("addUser")
    private Long addUser;
    @TableField("addTime")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaBugAdvice{" +
                ", id=" + id +
                ", isBugOrAdvice=" + isBugOrAdvice +
                ", content=" + content +
                ", qiyeID=" + qiyeID +
                ", kehufanduiDateTime=" + kehufankuiDateTime +
                ", addUser=" + addUser +
                ", addTime=" + addTime +
                "}";
    }
}
