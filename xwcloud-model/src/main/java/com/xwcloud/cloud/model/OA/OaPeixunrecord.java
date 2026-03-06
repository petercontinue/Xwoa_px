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
 * @since 2021-07-02
 */
@Data
@Accessors(chain = true)
@TableName("oa_peixunrecord")
public class OaPeixunrecord extends Model<OaPeixunrecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("addTime")
    private Date addTime;
    @TableField("addstaffID")
    private Long addstaffID;
    @TableField("pxcontent")
    private String pxcontent;
    @TableField("pxDate")
    private Date pxDate;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaPeixunrecord{" +
                ", id=" + id +
                ", addTime=" + addTime +
                ", addstaffID=" + addstaffID +
                ", pxcontent=" + pxcontent +
                ", pxDate=" + pxDate +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
